var cityMapping = {
    0: "南京市",
    1: "苏州市",
    2: "无锡市",
    3: "常州市",
    4: "南通市",
    5: "扬州市",
    6: "镇江市",
    7: "徐州市",
    8: "淮安市",
    9: "盐城市",
    10: "连云港市",
    11: "泰州市",
    12: "宿迁市"
    // 映射关系
};

var cityNode = {
    0: [118.802422, 32.064652],
    1: [120.585315, 31.298886],
    2: [120.31191, 31.491169],
    3: [119.973987, 31.810689],
    4: [120.856394, 32.016212],
    5: [119.412966, 32.39421],
    6: [119.425836, 32.187849],
    7: [117.284124, 34.205768],
    8: [119.021265, 33.597506],
    9: [120.163562, 33.347383],
    10:[119.22295, 34.59669],
    11:[119.915176, 32.484882],
    12:[118.275162, 33.963008]
}
function convertIdToCityName(id) {
    if (cityMapping[id]) {
        return cityMapping[id];
    } else {
        return '未知城市';
    }
}

function convertIdToCityLocal(id) {
    if (cityNode[id]) {
        return cityNode[id];
    } else {
        return false;
    }
}
