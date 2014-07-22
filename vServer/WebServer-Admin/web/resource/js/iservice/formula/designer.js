/**
 * 公式设计器代码
 */

function init(){
//    $('#main').split({
//        orientation:'horizontal',
//        limit:10
//    });

    $('#designer').height($('#main').height() - 205);
    $(window).resize(function() {
        $('#designer').height($('#main').height() - 205);
    });

    $('#setting').handsontable({
        startRows:7,
        startCols:30,
        height:198,
        multiSelect:false,
        colHeaders:function(col) {
            switch (col) {
                default :
                    return '<input type="checkbox" />';

            }
        },
        colWidths:80,
        rowHeaders:function(row) {
          switch (row) {
              case 0:
                  return '<div class="myheader">字段名称:</div>';
              case 1:
                  return '<div class="myheader">来源字段:</div>';
              case 2:
                  return '<div class="myheader">排序:</div>';
              case 3:
                  return '<div class="myheader">条件:</div>';
              case 4:
                  return '<div class="myheader">或者:</div>';
              default :
                  return '';

          }
        },
        width:function() {
            return $('#main').width();
        }

    });

};


jsPlumb.bind("ready", function(){

    var instance = jsPlumb.getInstance({
        Endpoint : ["Dot", {radius:2}],
        HoverPaintStyle : {strokeStyle:"#1e8151", lineWidth:2 },
        ConnectionOverlays : [
            [ "Arrow", {
                location:1,
                id:"arrow",
                length:14,
                foldback:0.8
            } ],
            [ "Label", { label:"FOO", id:"label", cssClass:"aLabel" }]
        ],
        Container:"designer"
    });


    var windows = jsPlumb.getSelector(".flowchart .window");


    instance.draggable(windows);


    instance.bind('connection', function(info){
        info.connection.getOverlay('label').setLabel(info.connection.id);
    });


    instance.doWhileSuspended(function(){
        instance.makeSource(windows, {
            filter:".ep",				// only supported by jquery
            anchor:"Continuous",
            connector:[ "Bezier" ],
            connectorStyle:{ strokeStyle:"#5c96bc", lineWidth:2, outlineColor:"transparent", outlineWidth:4 },
            maxConnections:5,
            onMaxConnections:function(info, e) {
                alert("Maximum connections (" + info.maxConnections + ") reached");
            }
        });


        instance.makeTarget(windows, {
            dropOptions: { hoverClass:'dragHover' },
            anchor:'Continuous'
        });

    });

    init();
});

