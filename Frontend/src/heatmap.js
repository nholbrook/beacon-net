console.log("TESTING");

var points = [];
var max = 0;

for(var i = 0; i < json.length; i++) {
    var obj = json[i];
    max = Math.max(max, obj.count);
    var point = {
        x: parseInt(obj.y, 10) * -1,
        y: parseInt(obj.x, 10),
        value: parseInt(obj.count, 10)
    };
    points.push(point);
}

var data = {
  max: max,
  data: points
};

console.log(data);

var baseLayer = L.tileLayer.zoomify('https://s3.amazonaws.com/beacon-net/bsb1/', {
    width: 3180,
    height: 2064,
    tolerance: 0.8
});

var cfg = {
    radius: 10,
    maxOpacity: .8,
    scaleRadius: true,
    useLocalExtrema: true,
    latField: 'x',
    lngField: 'y',
    valueField: 'value'
};

var heatmapLayer = new HeatmapOverlay(cfg);

var map = new L.Map('photo', {
  crs: L.CRS.Simple,
  center: [1590, 1032],
  zoom: 4,
  layers: [baseLayer, heatmapLayer]
});

heatmapLayer.setData(data);
