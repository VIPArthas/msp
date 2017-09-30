//JS 动态获取路径,无需多次配置
var localObj = window.location;
var basePath = localObj.protocol+"//"+localObj.host;
var SERVER_ROOT_PATH = basePath;