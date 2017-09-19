<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>

<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=8; IE=9">
  <meta name="viewport" content="width=device-width,initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <meta name="description" content="融数云途是一家技术驱动的科技创业公司，通过内容和数据的融合创新，为媒体和企业提供一体化信息管理和数据分析的平台。">
  <meta name="keywords" content="融合媒体,数据融合,媒体价值,一体化发布,内容管理,数据决策">
  <meta name="author" content="Fusio">
  <title>标签系统 | Fusio 融数云途</title>
  <c:set var="path" value="${pageContext.request.contextPath}/resources" />
  <link rel="shortcut icon" href="${path}/images/favicon.ico">
  <link href="${path}/css/bootstrap.min.css?v=14784338640234" rel="stylesheet">
  <link href="${path}/css/main.css?v=14784338640234" rel="stylesheet">
  <style type="text/css">
    html,body{height:100%;margin:0;padding:0;}
  </style>
  <script src="${path}/js/jquery/1.11.1/jquery.js"></script>
  <script src="${path}/js/g2/2.2.0/g2.js"></script>
</head>

<body style="padding-top: 80px; min-height: 200px; min-width: 1200px; overflow-x: hidden;">
  <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container" style="min-width: 1200px; overflow-x: hidden;">
      <div class="navbar-header">
        <a class="navbar-brand" href="#">Fusio 标签系统</a>
      </div>
      <div id="navbar" class="navbar-collapse collapse">
        <ul class="nav navbar-nav navbar-right" style="margin-right: 0px;">
          <li class="active"><a href="http://fusio.com.cn">去官网</a></li>
        </ul>
      </div>
    </div>
  </nav>
  <div class="container" style="width: 1200px; overflow-x: hidden; ">
    <div class="row">
      <div id="c1"></div>
    </div>
    <hr>
    <footer>
      <p>© Fusio 融数云途 2016</p>
    </footer>
  </div>

  <script type="text/javascript">
    var SITE_PATH = '${pageContext.request.contextPath}';
    // 初始化变量
    var chart, Stat;
    // 获取数据
    $.ajax({url: SITE_PATH + "/tagFront/getFullTagTree", success: function(result){
      // 处理数据
      var array = [];
      array.push(result);

      // var array = [{
      //   "name": "flare",
      //   "level": "0",
      //   "children": [{
      //     "name": "analytics",
      //     "level": "1",
      //     "collapsed": true,
      //     "children": [{
      //       "name": "cluster",
      //       "children": [{
      //         "name": "AgglomerativeCluster",
      //         "size": 3938
      //       }, {
      //         "name": "CommunityStructure",
      //         "size": 3812
      //       }, {
      //         "name": "HierarchicalCluster",
      //         "size": 6714
      //       }, {
      //         "name": "MergeEdge",
      //         "size": 743
      //       }]
      //     }, {
      //       "name": "graph",
      //       "children": [{
      //         "name": "BetweennessCentrality",
      //         "size": 3534
      //       }, {
      //         "name": "LinkDistance",
      //         "size": 5731
      //       }, {
      //         "name": "MaxFlowMinCut",
      //         "size": 7840
      //       }, {
      //         "name": "ShortestPaths",
      //         "size": 5914
      //       }, {
      //         "name": "SpanningTree",
      //         "size": 3416
      //       }]
      //     }, {
      //       "name": "optimization",
      //       "children": [{
      //         "name": "AspectRatioBanker",
      //         "size": 7074
      //       }]
      //     }]
      //   },{
      //     "name": "animate",
      //     "children": [{
      //       "name": "Easing",
      //       "size": 17010
      //     }, {
      //       "name": "FunctionSequence",
      //       "size": 5842
      //     }, {
      //       "name": "interpolate",
      //       "children": [{
      //         "name": "ArrayInterpolator",
      //         "size": 1983
      //       }, {
      //         "name": "ColorInterpolator",
      //         "size": 2047
      //       }, {
      //         "name": "DateInterpolator",
      //         "size": 1375
      //       }, {
      //         "name": "Interpolator",
      //         "size": 8746
      //       }, {
      //         "name": "MatrixInterpolator",
      //         "size": 2202
      //       }, {
      //         "name": "NumberInterpolator",
      //         "size": 1382
      //       }, {
      //         "name": "ObjectInterpolator",
      //         "size": 1629
      //       }, {
      //         "name": "PointInterpolator",
      //         "size": 1675
      //       }, {
      //         "name": "RectangleInterpolator",
      //         "size": 2042
      //       }]
      //     }, {
      //       "name": "ISchedulable",
      //       "size": 1041
      //     }, {
      //       "name": "Parallel",
      //       "size": 5176
      //     }, {
      //       "name": "Pause",
      //       "size": 449
      //     }, {
      //       "name": "Scheduler",
      //       "size": 5593
      //     }, {
      //       "name": "Sequence",
      //       "size": 5534
      //     }, {
      //       "name": "Transition",
      //       "size": 9201
      //     }, {
      //       "name": "Transitioner",
      //       "size": 19975
      //     }, {
      //       "name": "TransitionEvent",
      //       "size": 1116
      //     }, {
      //       "name": "Tween",
      //       "size": 6006
      //     }]
      //   }]
      // }];

      // 绘制树图
      drawTree(array);
    }});
      
    function drawTree(array) {
      var data = array;
      var Layout = G2.Layout;
      // 使用layout，用户可以自己编写自己的layout
      // 仅约定输出的节点 存在 id,x，y字段即可
      var layout = new Layout.Tree({
        nodes: data
      });
      var dx = layout.dx;
      var nodes = layout.getNodes();
      var edges = layout.getEdges();
      Stat = G2.Stat;
      chart = new G2.Chart({
        id: 'c1',
        width: 1000,
        height: 500,
        plotCfg: {
          margin: [20,50]
        },
        animate: false
      });
      // 不显示title
      chart.tooltip({
        title: null
      });
      chart.legend('children', false);
      chart.legend('name', false);
      renderTree(nodes, edges, dx);
      chart.on('plotclick', function(ev){
        var shape = ev.shape;
        if (shape) {
          var obj = shape.get('origin');
          var id = obj._origin.id;
          var node = layout.findNode(id);
          if (node && node.children) {
            node.collapsed = !node.collapsed ? 1 : 0;
            layout.reset();
            nodes = layout.getNodes();
            edges = layout.getEdges();
            dx = layout.dx;
            // edgeView.changeData(edges);
            renderTree(nodes, edges, dx);
          }
        }
      })
    };

    function drawNode(cfg, group, collapsed, isLeaf) {
      var x = cfg.x;
      var y = cfg.y;
      var pointSize = 5;
      var width = cfg.size;
      var height = 18;
      var label = cfg.label;
      var shape = group.addShape('rect', {
        attrs: {
          x: x,
          y: y - height / 2 ,
          width: width,
          height: height,
          fill: '#fff',
          cursor: isLeaf ? '' : 'pointer',
          stroke: cfg.color
        }
      });
      if (!isLeaf) {
        x = x - pointSize;
        group.addShape('circle', {
          attrs: {
            r: pointSize,
            x: x,
            y: y,
            fill: '#fff',
            stroke: cfg.color // 可以直接设置颜色 cfg.color，也可以使用映射
          }
        });
        var path = [];
        path.push(['M', x - pointSize/2, y]);
        path.push(['L', x + pointSize/2, y]);
        if (collapsed) {
          path.push(['M', x, y - pointSize/2]);
          path.push(['L', x, y + pointSize/2]);
        }
        group.addShape('path', {
          attrs: {
            path: path,
            stroke: cfg.color
          }
        });
      }
      return shape;
    }
    G2.Shape.registShape('point', 'collapsed', {
      drawShape: function(cfg, group) {
        return drawNode(cfg, group, true)
      }
    });
    G2.Shape.registShape('point', 'expanded', {
      drawShape: function(cfg, group) {
        return drawNode(cfg, group, false);
      }
    });
    G2.Shape.registShape('point', 'leaf', {
      drawShape: function(cfg, group) {
        return drawNode(cfg, group, false, true);
      }
    });

    function renderTree(nodes, edges, dx) {
      chart.clear();
      var height = Math.max(500, 26 / dx); // 最小高度 500
      chart.changeSize(1000, height);
      // 首先绘制 edges，点要在边的上面
      // 创建单独的视图
      var edgeView = chart.createView();
      edgeView.source(edges);
      edgeView.coord().transpose().scale(1, -1); //
      edgeView.axis(false);
      edgeView.tooltip(false);
      // Stat.link 方法会生成 ..x, ..y的字段类型，数值范围是 0-1
      edgeView.edge()
        .position(Stat.link('source*target',nodes))
        .shape('smooth')
        .color('#ccc');
      function strLen(str) {
        var len = 0;
        for (var i = 0; i < str.length; i ++) {
          if(str.charCodeAt(i) > 0 && str.charCodeAt(i) < 128) {
            len ++;
          } else {
            len += 2;
          }
        }
        return len;
      }
      // 创建节点视图
      var nodeView = chart.createView();
      nodeView.coord().transpose().scale(1, -1); //'polar'
      nodeView.axis(false);
      // 节点的x,y范围是 0，1
      // 因为边的范围也是 0,1所以正好统一起来
      nodeView.source(nodes, {
        x: {min: 0,max:1},
        y: {min: 0, max:1},
        value: {min: 0}
      },['id','x','y','name','children','collapsed']); // 由于数据中没有 'collapsed' 字段，所以需要设置所有的字段名称
      nodeView.point().position('x*y').color('steelblue').size('name', function(name) {
        var length = strLen(name);
        return length * 6 + 5 * 2;
      }).label('name', {
        offset: 6,
        labelEmit: true
      })
        .shape('children*collapsed', function(children,collapsed) {
        if (children) {
          if (collapsed) {
            return 'collapsed';
          } else {
            return 'expanded';
          }
        }
        return 'leaf';
      })
        .tooltip('name*id');
      chart.render();
    }
    


  </script>

</body>
</html>