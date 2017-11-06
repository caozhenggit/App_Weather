package com.caozheng.weather.util;

/**
 * @author 10744
 * @date 2017/11/6
 * <p>
 * describe:
 */

public class Api {

    public static final String WEATHER_API = "http://jisutianqi.market.alicloudapi.com/weather/query";

    public static final String KEY = "240548cc8c5b40a7ab1d0b41eafc701a";
    public static final String APP_KEY = "24683965";
    public static final String APP_SECRET = "ea684a8bc1186018ce23125dae8e8e1f";
    public static final String APP_CODE = "323b76f374544e35a100ff6d36a88ece";

//https://api.seniverse.com/v3/weather/now.json?key=sk0rdrypvhwhuz4k&location=beijing&language=zh-Hans&unit=c
//参数
//key   你的API密钥
//location  所查询的位置
//    参数值范围：
//    城市ID 例如：location=WX4FBXXFKE4F
//    城市中文名 例如：location=北京
//    省市名称组合 例如：location=辽宁朝阳、location=北京朝阳
//    城市拼音/英文名 例如：location=beijing（如拼音相同城市，可在之前加省份和空格，例：shanxi yulin）
//    经纬度 例如：location=39.93:116.40（格式是 纬度:经度，英文冒号分隔）
//    IP地址 例如：location=220.181.111.86（某些IP地址可能无法定位到城市）
//            “ip”两个字母 自动识别请求IP地址，例如：location=ip
//            language
//    语言 (可选)
//    参数值范围：点此查看
//            unit
//    单位 (可选)
//    参数值范围：
//    c 当参数为c时，温度c、风速km/h、能见度km、气压mb
//    f 当参数为f时，温度f、风速mph、能见度mile、气压inch
//    默认值：c
}
