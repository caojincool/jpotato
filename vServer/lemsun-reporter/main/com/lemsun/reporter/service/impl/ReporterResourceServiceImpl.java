package com.lemsun.reporter.service.impl;

import com.aspose.cells.*;
import com.aspose.cells.Cell;
import com.aspose.cells.Workbook;
import com.lemsun.core.*;
import com.lemsun.core.events.ResourceEvent;
import com.lemsun.core.jackson.JsonObjectMapper;
import com.lemsun.core.repository.ResourceRepository;
import com.lemsun.core.service.IResourceService;
import com.lemsun.core.service.impl.AbstractResourceSupportService;
import com.lemsun.reporter.ReporterInfo;
import com.lemsun.reporter.ReporterResource;
import com.lemsun.reporter.repository.ReporterResourceRepository;
import com.lemsun.reporter.service.IReporterResourceService;
import com.mongodb.gridfs.GridFSDBFile;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Date;
import java.util.UUID;

/**
 * 填报组件服务实现类
 * Created by dpyang on 2014/5/20.
 */
@Service
public class ReporterResourceServiceImpl extends AbstractResourceSupportService<ReporterResource> implements IReporterResourceService {

    private ReporterResourceRepository reporterResourceRepository;
    private final static String SheetName = "_lemsun_config_";
    private JsonObjectMapper mapper;



    @Autowired
    public ReporterResourceServiceImpl(ReporterResourceRepository reporterResourceRepository,
                                       IResourceService resourceService,
                                       JsonObjectMapper mapper
                                       ) {
        super(resourceService);
        this.reporterResourceRepository = reporterResourceRepository;
        this.mapper = mapper;
    }



    /**
     * 上传填报组件内容, 并将内容标记
     *
     * @param reporterResource 填报组件
     * @param stream      excel文件流
     */
    @Override
    public void uploadReporter(ReporterResource reporterResource, InputStream stream)  {
        LemsunResource resource = getResourceService().getBaseResource(reporterResource.getPid());
        uploadReporter(resource, reporterResource.getFileType(), stream);
    }

    @Override
    public void uploadReporter(LemsunResource resource, int reportertype, InputStream stream) {
        //临时保存文件
        Resource tempDir = Host.getInstance().getReporterUploadConfig().getTempDir();

        try
        {

            File tempFile = new File(tempDir.getFile(), UUID.randomUUID().toString());
            tempFile.createNewFile();
            try
            {


                FileOutputStream tempout = FileUtils.openOutputStream(tempFile);
                org.apache.commons.io.IOUtils.copy(stream, tempout);
                tempout.flush();
                tempout.close();
                stream.close();

                ReporterInfo inf = new ReporterInfo();
                inf.setResource(resource);
                inf.setUpdateTime(new Date());

                if(reportertype == ReporterResource.EXCEL_2010_TYPE
                        || reportertype == ReporterResource.EXCEL_TYPE)
                {
                    //uploadExcelAsposeReporter(reporterResource, inf, tempFile);

                    String filename = resource.getName() + "." + ReporterResource.GetTypeName(reportertype);

                    uploadExcelPoiReporter(resource, inf, tempFile, filename);
                }
            }
            catch (Exception ex)
            {
                throw ex;
            }
            finally {
                tempFile.delete();
            }
        }
        catch (Exception ex)
        {

        }
    }


    /**
     * 使用 POI 执行 Excel 执行 Excel 的填报类型组件创建, 在excel 文档中加入标记 sheet, 并将组件信息放置在 A1 单元格中
     * @param reporterResource
     * @param info
     * @param temp
     * @throws Exception
     */
    private void uploadExcelPoiReporter(LemsunResource reporterResource, ReporterInfo info, File temp, String filename) throws Exception
    {

        FileInputStream wkinput = FileUtils.openInputStream(temp);
        org.apache.poi.ss.usermodel.Workbook workbook =  WorkbookFactory.create(wkinput);
        wkinput.close();

        org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheet(SheetName);

        if(sheet == null)
        {
            sheet = workbook.createSheet(SheetName);
        }

        int sheetIndex = workbook.getSheetIndex(sheet);

        workbook.setSheetHidden(sheetIndex, org.apache.poi.ss.usermodel.Workbook.SHEET_STATE_HIDDEN);

        Row row = sheet.getRow(0);

        if(row == null)
        {
            row = sheet.createRow(0);
        }

        org.apache.poi.ss.usermodel.Cell cell = row.getCell(0, Row.CREATE_NULL_AS_BLANK);

        String resourceInfo = mapper.writeValueAsString(info);

        byte[] infoData = resourceInfo.getBytes("UTF-8");
        String infoStr = Base64.encodeBase64String(infoData);

        cell.setCellValue(infoStr);

        FileOutputStream out = FileUtils.openOutputStream(temp, false);

        workbook.write(out);
        out.flush();
        out.close();

        FileInputStream input = FileUtils.openInputStream(temp);
        reporterResourceRepository.uploadReporter(reporterResource, input, filename);
        input.close();
    }


    @Override
    public ResourceAttach getContentInfo(String pid) {
        return getResourceService().getAttachDetail(pid, getResourceService().getDbContentName(pid), null);
    }

    @Override
    public GridFSDBFile getReporterFile(ReporterResource reporterResource) {

        return getResourceService().getContentFile(reporterResource.getPid());
    }

    @Override
    public GridFSDBFile getReporterFile(LemsunResource resource) {
        return getResourceService().getContentFile(resource.getPid());
    }
}
