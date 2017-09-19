package com.fusio.tag.commons.exception;

/**
 * 错误代码规划<br>
 * 10000表示成功 <br>
 * 20000~29999表示业务错误<br>
 * 那么要保证他所抛出的业务代码和其他Service不一样<br>
 * 40000~49999表示异常<br>
 * 
 * @author Stone
 *
 */
public class ResultCode {

	/**
	 * 非公共业务错误码(允许重复)
	 * 
	 * @author Stone
	 *
	 */
	public static class Busi {
		// 20000~24999永锋用 25000~30000伟森用
		public static final String BUSI_20000 = "20000";
		public static final String BUSI_20001 = "20001";
		public static final String BUSI_20002 = "20002";
		public static final String BUSI_20003 = "20003";
		public static final String BUSI_20004 = "20004";
		public static final String BUSI_20005 = "20005";
		public static final String BUSI_20006 = "20006";
		public static final String BUSI_20007 = "20007";
		public static final String BUSI_20008 = "20008";
		public static final String BUSI_20009 = "20009";
		public static final String BUSI_20010 = "20010";
		public static final String BUSI_20011 = "20011";
		public static final String BUSI_20012 = "20012";
		public static final String BUSI_20013 = "20013";
		public static final String BUSI_20014 = "20014";
		public static final String BUSI_20015 = "20015";
		public static final String BUSI_20016 = "20016";
		public static final String BUSI_20017 = "20017";
		public static final String BUSI_20018 = "20018";
		public static final String BUSI_20019 = "20019";
		public static final String BUSI_20020 = "20020";
		public static final String BUSI_20021 = "20021";
		public static final String BUSI_20022 = "20022";
		public static final String BUSI_20023 = "20023";
		public static final String BUSI_20024 = "20024";
		public static final String BUSI_20025 = "20025";
		//
		public static final String BUSI_20026 = "20026";
		public static final String BUSI_20027 = "20027";
		public static final String BUSI_20028 = "20028";
		public static final String BUSI_20029 = "20029";
		public static final String BUSI_20030 = "20030";
		public static final String BUSI_20031 = "20031";
		public static final String BUSI_20032 = "20032";
		public static final String BUSI_20033 = "20033";
		public static final String BUSI_20034 = "20034";
		public static final String BUSI_20035 = "20035";
		//
		public static final String BUSI_20036 = "20036";
		public static final String BUSI_20037 = "20037";
		public static final String BUSI_20038 = "20038";
		public static final String BUSI_20039 = "20039";
		public static final String BUSI_20040 = "20040";
		public static final String BUSI_20041 = "20041";
		public static final String BUSI_20042 = "20042";
		public static final String BUSI_20043 = "20043";
		//
		public static final String BUSI_20044 = "20044";
		public static final String BUSI_20045 = "20045";
		public static final String BUSI_20046 = "20046";
		public static final String BUSI_20047 = "20047";
		public static final String BUSI_20048 = "20048";
		//
		public static final String BUSI_20049 = "20049";
		public static final String BUSI_20050 = "20050";
		
		// 
		public static final String BUSI_20051 = "20051";
		public static final String BUSI_20052 = "20052";
		public static final String BUSI_20055 = "20055";
		public static final String BUSI_20056 = "20056";
		public static final String BUSI_20057 = "20057";
		//
		public static final String BUSI_20058 = "20058";
		public static final String BUSI_20059 = "20059";
		public static final String BUSI_20060 = "20060";
		public static final String BUSI_20061 = "20061";
		public static final String BUSI_20062 = "20062";
		public static final String BUSI_20063 = "20063";
		public static final String BUSI_20064 = "20064";
		public static final String BUSI_20065 = "20065";
		public static final String BUSI_20066 = "20066";
		public static final String BUSI_20067 = "20067";
		public static final String BUSI_20068 = "20068";
		public static final String BUSI_20069 = "20069";
		public static final String BUSI_20070 = "20070";
		public static final String BUSI_20071 = "20071";
		public static final String BUSI_20072 = "20072";
		public static final String BUSI_20073 = "20073";
		public static final String BUSI_20074 = "20074";
		public static final String BUSI_20075 = "20075";
		public static final String BUSI_20076 = "20076";
		public static final String BUSI_20077 = "20077";
		public static final String BUSI_20078 = "20078";
		public static final String BUSI_20079 = "20079";
		public static final String BUSI_20080 = "20080";
		public static final String BUSI_20081 = "20081";// 撤销微博失败
		public static final String BUSI_20082 = "20082";// 微博发不过
		public static final String BUSI_20083 = "20083";// 一点已经发送过
		public static final String BUSI_20084 = "20084";// 头条已经发送过
		public static final String BUSI_20085 = "20085";// 微信已经发送过
		public static final String BUSI_20086 = "20086";// 微信发布失败
		public static final String BUSI_20087 = "20087";// 撤销微信发布失败,单个
		public static final String BUSI_20088 = "20088";// 已经删除过微信
		public static final String BUSI_20089 = "20089";// 已经删除过微博
		public static final String BUSI_20090 = "20090";// 不是自己发的微博不能删除
		
		
		
		
		// 25000~30000伟森用
		public static final String BUSI_25000 = "25000";   //修改失败，数据异常！    
		public static final String BUSI_25001 = "25001";   //添加数据失败！    
		public static final String BUSI_25002 = "25002";    //该用户已是激活用户！
		public static final String BUSI_25003 = "25003";     //密码校验失败  
		public static final String BUSI_25004 = "25004";     //该ID查询的对应数据为空
		public static final String BUSI_25005 = "25005";     //非一级管理员和创建用户不能修改 
		public static final String BUSI_25006 = "25006";     //类型错误，不存在该类型 
		public static final String BUSI_25007 = "25007";      //该数据已是发布状态
		public static final String BUSI_25008 = "25008";       //该数据已是下架状态
		public static final String BUSI_25009 = "25009";       //该姓名已存在
		public static final String BUSI_25010 = "25010";       //找不到数据 
		public static final String BUSI_25011 = "25011";       //查询出的数据数量不正确
		public static final String BUSI_25012 = "25012";       //每次显示数比总数还多
		public static final String BUSI_25013 = "25013";       //数据异常，操作失败
		public static final String BUSI_25014 = "25014";       //该杂志已经是订阅状态
		public static final String BUSI_25015 = "25015";       //没有找到杂志刊物
		public static final String BUSI_25016 = "25016";       //没有排在该位置的杂志刊物
		public static final String BUSI_25017 = "25017";       //非管理员不可操作
		public static final String BUSI_25018 = "25018";       //图说图片数量少于5张，数据不保存
		public static final String BUSI_25019 = "25019";       //图说图片数量少于5张，数据不修改
		public static final String BUSI_25020 = "25020";       //发布日期只能是今天或之后
		public static final String BUSI_25021 = "25021";       //该发布时间已存在头条
		public static final String BUSI_25022 = "25022";       //类型错误，该数据不存在该类型操作
		public static final String BUSI_25023 = "25023";       //该用户已绑定邮箱
		public static final String BUSI_25024 = "25024";       //该用户已绑定QQ
		public static final String BUSI_25025 = "25025";       //该QQ已绑定账号，请先解绑
		public static final String BUSI_25026 = "25026";        //该微博已绑定账号，请先解绑
		public static final String BUSI_25027 = "25027";       //该用户已绑定微博号
		public static final String BUSI_25028 = "25028";       //需先绑定邮箱，才能解绑该账号
		public static final String BUSI_25029 = "25029";       //昵称已存在，昵称不能相同
		public static final String BUSI_25030 = "25030";       //删除失败，ID错误或用户没权限
		public static final String BUSI_25031 = "25031";       // 报刊不存在add by stone
		public static final String BUSI_25032 = "25032";       //结束时间必须大于开始时间
		public static final String BUSI_25033 = "25033";       //创建杂志目录与模板内容不匹配！
		public static final String BUSI_25034 = "25034";       
		public static final String BUSI_25035 = "25035";       
		public static final String BUSI_25036 = "25036";       
		public static final String BUSI_25037 = "25037";       
		public static final String BUSI_25038 = "25038";       
		public static final String BUSI_25039 = "25039";       
		public static final String BUSI_25040 = "25040";       
		public static final String BUSI_25041 = "25041";       
		public static final String BUSI_25042 = "25042";       
		public static final String BUSI_25043 = "25043";       
		public static final String BUSI_25044 = "25044";       
		public static final String BUSI_25045 = "25045";       
		public static final String BUSI_25046 = "25046";       
		public static final String BUSI_25047 = "25047";       
		public static final String BUSI_25048 = "25048";       
		public static final String BUSI_25049 = "25049";       
		public static final String BUSI_25050 = "25050";       


	}

	/**
	 * 默认的成功代码和消息
	 */
	public static final String SUCCESS_CODE = "10000";
	public static final String SUCCESS_MSG = "成功";

	/**
	 * 请求的接口不存在
	 */
	public static final String METHOD_NOT_FOUND_CODE = "40100";
	public static final String METHOD_NOT_FOUND_MSG = "接口访问方式不正确(GET/POST/PUT/DELETE...)";

	/**
	 * 接口参数错误<br>
	 * 必传的没传,参数类型转换错误,或者手动抛出InvalidParamException(有的时候验证框架并不完美,需要手动验证并抛出)等等<br>
	 * 
	 * validtion框架验证不通过
	 */
	public static final String WRONG_METHOD_PARAM_CODE = "40200";
	public static final String WRONG_METHOD_PARAM_MSG = "接口参数错误(少传、格式或类型错误)";

	/**
	 * 数据问题(残缺)错误
	 */
	public static final String INVALID_DATA_CODE = "40300";
	public static final String INVALID_DATA_MSG = "数据问题(残缺)错误";

	/**
	 * 没有权限调用接口(摘要错误)
	 */
	public static final String NOT_AUTH_CODE = "40400";
	public static final String NOT_AUTH_MSG = "没有权限调用接口(摘要错误)";

	/**
	 * 意料之外的运行时异常
	 */
	public static final String RUNTIME_EX_CODE = "40500";
	public static final String RUNTIME_EX_MSG = "意料之外的运行时异常";
}