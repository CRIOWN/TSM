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
    10: "连云港",
    11: "泰州市",
    12: "宿迁市"
    // 映射关系
};
function convertIdToCityName(id) {
    if (cityMapping[id]) {
        return cityMapping[id];
    } else {
        return '未知城市';
    }
}