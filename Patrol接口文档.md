# Patrol接口文档

## 1  事件管理模块

### 1.1  新增事件单

#### 1.1.1  功能说明 

针对故障设备创建事件单

#### 1.1.2  接口路径

请求方式：**POST**

请求路径：

~~~ http
http://ip:10000/patrol/event/add
~~~

#### 1.1.3  参数说明

| 参数        | 说明                                     | 是否必须 | 数据类型 | 默认值 |
| ----------- | ---------------------------------------- | -------- | -------- | ------ |
| eventNum    | 事件单编号                               | 是       | String   | 无     |
| equipmentId | 设备Id                                   | 是       | String   | 无     |
| location    | 设备故障位置                             | 否       | String   | 无     |
| problem     | 设备故障描述                             | 否       | String   | 无     |
| model       |                                          |          | String   | 无     |
| roomNum     | 设备所在房间编号                         | 否       | String   | 无     |
| cause       | 设备故障原因                             | 否       | String   | 无     |
| solution    | 解决方案                                 | 否       | String   | 无     |
| isCompleted | 设备故障问题是否解决，0：解决，1：未解决 | 否       | Integer  | 无     |
| isDeleted   | 逻辑删除标识，0：删除，1：未删除         | 否       | Integer  | 无     |
| version     | 版本号                                   | 否       | String   | 无     |

#### 1.1.4  返回结果

返回结果成功示例：

~~~ json
{
    "code": 0,
    "message": "Operation Success",
    "data": null
}
~~~

返回结果失败示例：

~~~ json
{
    "code": 1,
    "message": "Operation Failure",
    "data": null
}
~~~

状态码：

- 200：新增成功
- 500：服务器内部异常，新增失败

### 1.2  根据事件单编号查询事件单

#### 1.2.1  功能说明 

根据事件单编号查询事件单数据

#### 1.2.2  接口路径

请求方式：**GET**

请求路径：

~~~ http
http://ip:10000/patrol/event/query/{eventNum}
~~~

#### 1.2.3  参数说明

| 参数     | 说明       | 是否必须 | 数据类型 | 默认值 |
| -------- | ---------- | -------- | -------- | ------ |
| eventNum | 事件单编号 | 是       | String   | 无     |

#### 1.2.4  返回结果

返回结果成功示例：

~~~ json
{
  "code": 0,
  "message": "Operation Success",
  "data": {
    "createdTime": "2019-12-05 10:22:29",
    "updatedTime": "2019-12-05 10:22:29",
    "id": 36,
    "eventNum": "3164",
    "equipmentId": "271",
    "location": "3号楼西3-22",
    "model": "模型A",
    "roomNum": "995",
    "problem": "电池故障告警",
    "cause": "停止工作",
    "solution": null,
    "isCompleted": 1,
    "isDeleted": 1,
    "version": "V1.0"
  }
}
~~~

状态码：

- 200：查询成功
- 400：事件单编号错误
- 500：服务器内部异常，查询失败



### 1.3 根据事件单编号修改事件单

#### 1.3.1  功能说明 

根据事件单编号更新事件单数据，关闭相应事件单

#### 1.3.2  接口路径

请求方式：PUT

请求路径：

~~~ http
http://ip:10000/patrol/event/update/status
~~~

#### 1.3.3  参数说明

| 参数        | 说明                                       | 是否必须 | 数据类型 | 默认值 |
| ----------- | ------------------------------------------ | -------- | -------- | ------ |
| id          | 事件表主键                                 | 是       | Integer  | 无     |
| eventNum    | 事件单编号                                 | 是       | String   | 无     |
| equipmentId | 设备编号                                   | 是       | String   | 无     |
| location    | 设备故障发生位置                           | 是       | String   | 无     |
| roomNum     | 房间编号                                   | 是       | String   | 无     |
| isCompleted | 设备故障问题是否解决，0：已解决，1：未解决 | 是       | Integer  | 无     |

#### 1.3.4  返回结果

返回结果成功示例：

```json
{
    "code": 0,
    "message": "Operation Success",
    "data": null
}
```

返回结果失败示例：

```json
{
    "code": 1,
    "message": "Operation Failure",
    "data": null
}
```

### 1.4  根据事件单编号删除事件单

#### 1.4.1  功能说明 

根据事件单编号删除事件单，只做逻辑删除，不做物理删除

#### 1.4.2  接口路径

请求方式：**DELETE**

请求路径：

~~~ http
http://ip:10000/patrol/event/delete/{eventNum}
~~~

#### 1.4.3  参数说明

| 参数     | 说明       | 是否必须 | 数据类型 | 默认值 |
| -------- | ---------- | -------- | -------- | ------ |
| eventNum | 事件单编号 | 是       | String   | 无     |

#### 1.4.4  返回结果

返回结果成功示例：

```json
{
    "code": 0,
    "message": "Operation Success",
    "data": null
}
```

返回结果失败示例：

```json
{
    "code": 1,
    "message": "Operation Failure",
    "data": null
}
```

状态码：

- 200：新增成功
- 500：服务器内部异常，新增失败

### 1.5 根据事件单创建时间段查询事件单

#### 1.5.1  功能说明

根据事件单创建时间段查询事件单，例如可指定查询开始时间为2019-12-05 09:00:00，结束时间为2019-12-05 11:00:00的这段时间内创建的事件单数量

#### 1.5.2  接口路径

请求方式：**GET**

请求路径：

~~~ http
http://localhost:10000/patrol/event/queryPeriod?startTime=2019-12-05%2009:00:00&endTime=2019-12-05%2011:00:00
~~~

#### 1.5.3  参数说明

| 参数      | 说明         | 是否必须 | 数据类型 | 默认值 |
| --------- | ------------ | -------- | -------- | ------ |
| startTime | 查询开始时间 | 是       | String   | 无     |
| endTime   | 查询结束时间 | 是       | String   | 无     |

#### 1.5.4  返回结果

返回结果与调用根据事件单编号查询事件单的接口返回的结果类似，返回多条事件单数据

状态码：

- 200：查询成功
- 400：请求参数错误
- 500：服务器内部异常，查询失败