var map, toolbar, symbol, geomTask, configOptions, graphic, symbol, CapaProyectos;
var urlproy = "https://hermes.invias.gov.co/arcgis/rest/services/Proyectos_Planeacion/Proyectos/FeatureServer/0";
var idProyecto = ""; /*Eliminar los valores*/
var fields = ["cod_proy", "cod_tram", "OBJECTID"];

require([
    "dojo/_base/lang", "dojo/dom", "dojo/html", "dojo/_base/json", "esri/layers/FeatureLayer", "esri/tasks/query",
    "esri/map",
    "esri/arcgis/utils",
    "esri/toolbars/draw",
    "esri/graphic",
    "esri/symbols/SimpleMarkerSymbol",
    "esri/symbols/SimpleLineSymbol",
    "esri/symbols/SimpleFillSymbol",
    "esri/geometry/Polyline",
    "dojo/parser", 
    "dijit/registry",
    "esri/Color",
    "dijit/layout/BorderContainer", "dijit/layout/ContentPane",
    "dijit/form/Button", "dijit/WidgetSet", "dojo/domReady!"
], function (lang, dom, html, json, FeatureLayer, Query,
        Map, utils, Draw, Graphic,
        SimpleMarkerSymbol, SimpleLineSymbol, SimpleFillSymbol,Polyline,
        parser, registry, Color
        ) {
    parser.parse();

    /* map = new Map("map", {
     basemap: "streets",
     center: [-15.469, 36.428],
     zoom: 3
     });*/

    configOptions = {
        webmap: "0a6cc047d073417cae6f251c222f5c6a",
        title: "",
        subtitle: "",
        sharingurl: "https://www.arcgis.com/sharing/rest/content/items"
    };
    var mapDeferred = esri.arcgis.utils.createMap(configOptions.webmap, "map");
    mapDeferred.then(function (response) {
        map = response.map;
        map.graphics.clear();
        if (document.getElementById("frmData:contrato_id").value !== "") {
            idProyecto = document.getElementById("frmData:contrato_id").value;
        }
        consultar();
        
        // loop through all dijits, connect onClick event
        // listeners for buttons to activate drawing tools
        registry.forEach(function (d) {
            if (d.id === "ZoomItem") {
                d.on("click", lang.hitch(this, zoomItem));
            }

        });
    });
    function  zoomItem() {
        var idTramo = document.getElementById("frmData:tramo_id").value;
        CapaProyectos = new FeatureLayer(urlproy, {outFields: fields});
        CapaProyectos.setEditable(true);
        var sqlExpression = "cod_proy = '" + idProyecto + "' and cod_tram='" + idTramo + "'";
        var queryParams = new Query();
        queryParams.outFields = fields;
        queryParams.where = sqlExpression;
        CapaProyectos.queryFeatures(queryParams, getStatsZoom, errback);
    }
    function getStatsZoom(results) {
        var i = 0;
        if (results.features.length > 0)
        {
            var extend = results.features[0].geometry.getExtent().expand(5.0);
            map.setExtent(extend);
        } else {
            console.log("No hay nada");
        }
    }

    function consultar() {
        //alert ("se va hacer la consulta");
        CapaProyectos = new FeatureLayer(urlproy, {outFields: fields});
        CapaProyectos.setEditable(true);
        var sqlExpression = "cod_proy = '" + idProyecto + "'";
        var queryParams = new Query();
        queryParams.outFields = fields;
        queryParams.where = sqlExpression;
        CapaProyectos.queryFeatures(queryParams, getStats, errback);
    }
    function getStats(results) {
        var i = 0;
        var red =0;
        var blue =0;
        var green =0;
        var x_min;
        var x_max;
        var y_min;
        var y_max;
        if (results.features.length > 0)
        {
            for (i = 0; i < results.features.length; i++) {
                var stats = results.features[i].geometry;
                red = Math.floor(Math.random() * 250) + 1;
                blue = Math.floor(Math.random() * 250) + 1;
                green = Math.floor(Math.random() * 220) + 1;
                symbol = new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID, new Color([red, green, blue]), 10);
                graphic = new Graphic(stats, symbol);
                graphic.setAttributes(results.features[i].attributes);
                /*Determinar el minimo y maximo para el extend*/
                for(var i=0;i< graphic.geometry.paths[0].length;i++){
                    if(x_min === null){
                        x_min = graphic.geometry.paths[0][i][0];
                        x_max = graphic.geometry.paths[0][i][0];
                        y_min = graphic.geometry.paths[0][i][1];
                        y_max = graphic.geometry.paths[0][i][1];
                    }
                    else{
                        if(graphic.geometry.paths[0][i][0] < x_min ){
                            x_min = graphic.geometry.paths[0][i][0];
                        }
                        if(graphic.geometry.paths[0][i][0] > x_max){
                           x_max = graphic.geometry.paths[0][i][0];
                        }
                        if(graphic.geometry.paths[0][i][1] < y_min ){
                            y_min = graphic.geometry.paths[0][i][1];
                        }
                        if(graphic.geometry.paths[0][i][1] > y_max){
                           y_max = graphic.geometry.paths[0][i][1];
                        }
                    }
                    
                }
                graphic.geometry.paths[0].length
                console.log(graphic.geometry.getExtent);
                map.graphics.add(graphic);
            }
            var extend = results.features[0].geometry.getExtent().expand(5.0);
            map.setExtent(map.extent);
        } else {
            console.log("No hay nada");
        }
    }

    function errback(err) {
        console.log("Couldn't retrieve summary statistics. ", err);
    }


});