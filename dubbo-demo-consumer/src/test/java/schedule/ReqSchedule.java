package schedule;

import com.alibaba.fastjson.JSONObject;
import com.qaf.utils.HttpClientUtil;

/**
 * @author 周 浩
 * @email zhou_eric90@163.com
 * @date 2018年6月4日 下午2:25:21
 * @描述
 */
public class ReqSchedule {

	public static void main(String[] args) {
		String url = "http://127.0.0.1:8080/schedule/createTask";
		JSONObject object = new JSONObject();
		object.put("taskName", "Task1");
		object.put("groupName", "TaskGroup");
		object.put("cronExpression", "*/10 * * * * ?");
		object.put("replace", "true");
		String result = HttpClientUtil.postSendJson(url, object.toJSONString());
		System.out.println(result);
	}

}
