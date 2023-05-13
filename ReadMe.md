Ajax-post请求无法返回视图


2.init.json


3.json 数据接口请求异常：
传送格式不对

4.Parameter 'name' not found. Available parameters are [arg2, arg1, arg0, param3, param1, param2]
在Mapper加@Param ==ClientMapper.java

5 sql删除后自增不连续
因为默认数据库引擎innodb是通过一个变量递加来实现id自增
重启数据库可以重置 让数据连续 

6.导入依赖后仍显示
java.lang.ClassNotFoundException: com.google.common.base.Splitter
java.lang.NoClassDefFoundError: com/google/common/base/Splitter
项目lib中未手动添加

6. layui参数
   contentType ：发送到服务端的内容编码类型。如果你要发送 json 内容，可以设置：contentType: 'application/json'
   Java中的List是一种有序的集合，可以存储重复元素，常用的函数包括：

   add(E e)：向列表末尾添加元素e；
   add(int index, E element)：在指定索引位置插入元素element；
   remove(int index)：删除指定索引位置的元素；
   remove(Object o)：删除指定元素o；
   clear()：清空列表中所有元素；
   size()：返回列表中元素的数量；
   get(int index)：返回指定索引位置的元素；
   set(int index, E element)：将指定索引位置的元素替换为element；
   contains(Object o)：判断列表是否包含指定元素o；
   indexOf(Object o)：返回指定元素o在列表中第一次出现的索引位置；
   lastIndexOf(Object o)：返回指定元素o在列表中最后一次出现的索引位置；
   subList(int fromIndex, int toIndex)：返回列表中从fromIndex（包含）到toIndex（不包含）之间的子列表。

除此之外，还可以使用迭代器（Iterator）对列表中的元素进行遍历和访问。

7. 删除触发器
```sql
DELIMITER $$

CREATE
    /*[DEFINER = { user | CURRENT_USER }]*/
    TRIGGER `tms`.`de_admin_id` AFTER DELETE
    ON `tms`.`admin`
    FOR EACH ROW BEGIN
    DELETE
    FROM `tms`.`admin_log`
    WHERE admin_log.userid = OLD.userid;
END$$

DELIMITER ;

```

8.        读map
 ```console.log("M1::"+result.data['admin']);```

9. eCharts的legend不显示对应名称
饼图类似data为： name要和legend相同
```js
  data:[
 {value: admin,name:"管理员"},
{value:staff,name:"员工"},
  {value:client,name:"客户"},
```

10. 字符串模板
```    
模板变量有：
    {a}：系列名。
    {b}：数据名。
    {c}：数据值。
    {@xxx}：数据中名为 'xxx' 的维度的值，如 {@product} 表示名为 'product' 的维度的值。
    {@[n]}：数据中维度 n 的值，如 {@[3]} 表示维度 3 的值，从 0 开始计数。
```

11. 伪欧式距离
```
计算伪欧式距离需要对每个特征进行加权，并使用加权后的欧式距离公式来计算距离。以下是计算伪欧式距离的步骤：
    定义特征权重向量 w，其中 w[i] 表示第 i 个特征的权重。这些权重可以是任意值，但必须大于 0。
    对每个特征进行归一化，将它们缩放到相同的范围内。这可以通过将每个特征减去其均值，然后除以其标准差来实现。
    对于两个向量 x 和 y，将它们的每个元素按照权重向量 w 进行加权，得到加权后的向量 x' 和 y'。
    计算加权后的欧式距离，即：
    d(x, y) = sqrt(sum((x'[i] - y'[i])^2))
其中，sum 表示对所有 i 进行求和，sqrt 表示计算平方根。

注意，当权重向量 w 的所有元素都相等时，伪欧式距离就变成了普通的欧式距离。
```

12. 江苏数据集对照
```
0  32.0581 118.7969  # 南京市 0  
1  31.2983 120.5832  # 苏州市 1
2  31.5747 120.3055  # 无锡市 2
3  31.8108 119.9731  # 常州市 3
4  32.0162 120.8686  # 南通市 4
5  32.3942 119.4128  # 扬州市 5
6  32.2044 119.4528  # 镇江市 6
7  34.2715 117.1885  # 徐州市 7
8  33.5975 119.0213  # 淮安市 8
9  33.3798 120.1436  # 盐城市 9
10 34.6000 119.1788  # 连云港 10
11 32.5714 120.0608  # 泰州市 11 
12 33.7847 118.5263  # 宿迁市 12


====邻接矩阵====
0 6139 5010 3801 6553 2219 2126 8653 4920 5967 
6139 0 1240 2520 2444 5071 4582 14271 8790 6728 
5010 1240 0 1290 2263 3833 3353 13034 7577 5732 
3801 2520 1290 0 2906 2558 2064 11752 6402 4991 
6553 2444 2263 2906 0 4757 4517 13649 7690 4884 
2219 5071 3833 2558 4757 0 614 9205 4002 3881 
2126 4582 3353 2064 4517 614 0 9696 4612 4312 
8653 14271 13034 11752 13649 9205 9696 0 6176 9762 
4920 8790 7577 6402 7690 4002 4612 6176 0 3616 
5967 6728 5732 4991 4884 3881 4312 9762 3616 0 
=====邻接矩阵======

6->8=9205
7->8=9696(6.7)==10
迪杰斯特拉根据邻近矩阵计算 
最优路径单独计算 
```

13. 优先队列
```js
    add(E e) / offer(E e): 将元素添加到优先队列中。
    remove() / poll(): 移除并返回队列中最高优先级的元素。
    peek(): 返回队列中最高优先级的元素，但不会将其从队列中删除。
    size(): 返回队列中的元素个数。
    clear(): 移除队列中的所有元素。
    toArray(): 返回一个包含队列中所有元素的数组。
    comparator(): 返回用于排序的比较器。如果使用默认的排序顺序，则返回null。
```

14. MD5对照表
```
111111：：36bf7ce3f6c94cfb3f5a5f789bb5a734
222222：：201c7c50d4a55d7ba24e87f65d30c311
333333：：5376b99396a3df93329f5a6f5322d7fa
123456：：5985663f60fa37fd5476155c867d7676
```


15. tets
```js
,
                    {
                        name: "line",
                        type: 'lines',
                        coordinateSystem: 'geo',
                        polyline: true,
                        data:{
                            coords:[
                                [118.802422, 32.064652], [120.31191, 31.491169]
                            ]
                        },
                        lineStyle: {
                            color: "#0e00fe",
                            width: 0,
                            type: 'solid'
                        },
                        effect: {
                            constantSpeed: 20,
                            show: true,
                            trailLength: 0.1,
                            symbolSize: 1.5
                        }
                    }
```
```js
,
                        markLine:{
                            silent:false,
                            label:{
                                show:true,
                                position:'middle',
                                color: '#070707',
                                fontStyle:'normal',
                                data:{
                                }
                            },

```
```js
,
                    {
                        type: 'lines',
                        name: "line",
                        coordinateSystem: 'geo',
                        polyline: true,
                        effect: {
                            constantSpeed: 20,
                            show: true,
                            trailLength: 0.1,
                            symbolSize: 1.5
                        },
                        lineStyle: {
                            color: "#0e00fe",
                            width: 0,
                            type: 'solid'
                        },
                        data:{
                            fromName: '南京', toName: '苏州', coords: [[118.8024, 32.0647], [120.31191, 31.491169]]
                        }
                    }
```
```js
.map(function (item) {
                            return {
                                fromName: item.fromName,
                                toName: item.toName,
                                coords: [item.coords[0].concat(0), item.coords[1].concat(0)]
                            };})
```
```js
  {
                        name: '江苏路径图',
                        type: 'map',
                        map: 'jiangsu',
                        label: {
                            show: true
                        },
                        roam: true,
                        data: [
                        ],
                        markPoint: {
                            symbol: "circle",
                            symbolSize: 10,
                            label: {
                                show: false,
                                formatter: '{b}'
                            },
                            itemStyle: {
                                color: '#F00'
                            },
                            data: [
                                {name: '南京市', coord: [118.802422, 32.064652]},
                                {name: '无锡市', coord: [120.31191, 31.491169]},
                                {name: '徐州市', coord: [117.284124, 34.205768]},
                                {name: '常州市', coord: [119.973987, 31.810689]},
                                {name: '苏州市', coord: [120.585315, 31.298886]},
                                {name: '南通市', coord: [120.856394, 32.016212]},
                                {name: '连云港市', coord: [119.22295, 34.59669]},
                                {name: '淮安市', coord: [119.021265, 33.597506]},
                                {name: '盐城市', coord: [120.163562, 33.347383]},
                                {name: '扬州市', coord: [119.412966, 32.39421]},
                                {name: '镇江市', coord: [119.425836, 32.187849]},
                                {name: '泰州市', coord: [119.915176, 32.484882]},
                                {name: '宿迁市', coord: [118.275162, 33.963008]}
                            ]
                        },
                        },

```
```js
  markPoint: {
                            symbol: "circle",
                            symbolSize: 10,
                            label: {
                                show: false,
                                formatter: '{b}'
                            },
                            itemStyle: {
                                color: '#F00'
                            },
                            data: [
                                {name: '南京市', coord: [118.802422, 32.064652]},
                                {name: '无锡市', coord: [120.31191, 31.491169]},
                                {name: '徐州市', coord: [117.284124, 34.205768]},
                                {name: '常州市', coord: [119.973987, 31.810689]},
                                {name: '苏州市', coord: [120.585315, 31.298886]},
                                {name: '南通市', coord: [120.856394, 32.016212]},
                                {name: '连云港市', coord: [119.22295, 34.59669]},
                                {name: '淮安市', coord: [119.021265, 33.597506]},
                                {name: '盐城市', coord: [120.163562, 33.347383]},
                                {name: '扬州市', coord: [119.412966, 32.39421]},
                                {name: '镇江市', coord: [119.425836, 32.187849]},
                                {name: '泰州市', coord: [119.915176, 32.484882]},
                                {name: '宿迁市', coord: [118.275162, 33.963008]}
                            ]
                        },
```