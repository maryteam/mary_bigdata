/**
 * Created by gssflyaway on 16/1/17.
 */

$(document).ready(function () {
    var myChart = echarts.init(document.getElementById('morris-area-chart1'));

    option = {
        color : [
            '#FBB367','#80B1D2','#FB8070','#CC99FF','#B0D961',
            '#99CCCC','#BEBBD8','#FFCC99','#8DD3C8','#FF9999',
            '#CCEAC4','#BB81BC','#FBCCEC','#CCFF66','#99CC66',
            '#66CC66','#FF6666','#FFED6F','#ff7f50','#87cefa',
        ],
        title : {
            text : '中东地区的敌友关系',
            subtext: '数据来自财新网',
            sublink: 'http://international.caixin.com/2013-09-06/100579154.html',
            x:'right',
            y:'bottom'
        },
        toolbox: {
            show : true,
            feature : {
                restore : {show: true},
                magicType: {show: true, type: ['force', 'chord']},
                saveAsImage : {show: true}
            }
        },
        tooltip : {
            trigger: 'item',
            formatter : function (params) {
                if (params.name && params.name.indexOf('-') != -1) {
                    return params.name.replace('-', ' ' + params.seriesName + ' ')
                }
                else {
                    return params.name ? params.name : params.data.id
                }
            }
        },
        legend : {
            data : [
                'jd',
                'szzc',
                'tx',
                'mpw',
                'yl',
                'edj',
                'dd',

                '',
                '支持'
            ],
            orient : 'vertical',
            x : 'left'
        },
        series : [
            {
                "name": "联系",
                "type": "chord",
                "showScaleText": false,
                "clockWise": false,
                "data": [
                    {"name": "jd"},
                    {"name": "tx"},
                    {"name": "szzc"},
                    {"name": "mpw"},
                    {"name": "yl"},
                    {"name": "edj"},
                    {"name": "dd"},
                ],
                "matrix": [
                    [0,45029,25876,1080,1421,1050,0],
                    [45029,0,11266,1149,1153,0,0],
                    [25876,11266,0,2473,2805,3169,1224],
                    [1080,1149,2473,0,0,0,0],
                    [1421,1153,2805,0,0,0,0],
                    [1050,0,3169,0,0,0,0],
                    [0,0,1224,0,0,0,0]
                ]
            }
        ]
    };


    myChart.setOption(option);

})