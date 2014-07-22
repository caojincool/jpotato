package com.lemsun.data.tables;

import com.lemsun.core.SpringContextUtil;
import com.lemsun.core.jackson.JsonObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 表格显示设置
 * User: Xudong
 * Date: 12-11-16
 * Time: 下午3:19
 */
public class TableFace {

	private List<FaceSetting> settings = new ArrayList<>();
	private boolean border = true;
	private String font;
	private List<CellRange> headerSpan = new ArrayList<>();
	private List<CellRange> cellsSpan = new ArrayList<>();
	private int headerColCount;
	private int headerRowCount;



	/**
	 * 获取自定义的界面设置
	 */
	public List<FaceSetting> getSettings() {
		return settings;
	}

	/**
	 * 界面设置
	 */
	public void setSettings(List<FaceSetting> settings) {
		this.settings = settings;
	}

	/**
	 * 是否显示表格边框
	 */
	public boolean isBorder() {
		return border;
	}

	/**
	 * 设置显示表格边框
	 */
	public void setBorder(boolean border) {
		this.border = border;
	}

	/**
	 * 获取显示字体
	 */
	public String getFont() {
		return font;
	}

	/**
	 * 设置显示字体
	 */
	public void setFont(String font) {
		this.font = font;
	}

	/**
	 * 获取表头合并区域
	 */
	public List<CellRange> getHeaderSpan() {
		return headerSpan;
	}

	/**
	 * 设置表头合并区域
	 */
	public void setHeaderSpan(List<CellRange> headerSpan) {
		this.headerSpan = headerSpan;
	}

	/**
	 * 获取单元格合并内容
	 */
	public List<CellRange> getCellsSpan() {
		return cellsSpan;
	}

	/**
	 * 设置单元格合并内容
	 */
	public void setCellsSpan(List<CellRange> cellsSpan) {
		this.cellsSpan = cellsSpan;
	}

	/**
	 * 获取表头列数
	 */
	public int getHeaderColCount() {
		return headerColCount;
	}

	/**
	 * 设置表头列数
	 */
	public void setHeaderColCount(int headerColCount) {
		this.headerColCount = headerColCount;
	}

	/**
	 * 获取表头行数
	 */
	public int getHeaderRowCount() {
		return headerRowCount;
	}

	/**
	 * 设置表头行数
	 */
	public void setHeaderRowCount(int headerRowCount) {
		this.headerRowCount = headerRowCount;
	}

    /**
     * 将皮肤设置信息转换成JSON数据
     */
    public String toJson()
    {
        JsonObjectMapper mapper = SpringContextUtil.getBean(JsonObjectMapper.class);

        try {
            return mapper.writeValueAsString(this);
        } catch (IOException e) {
            try {
                return "{error: " + mapper.writeValueAsString(e.getMessage()) + " }";
            } catch (IOException e1) {

            }

            return null;
        }
    }
}
