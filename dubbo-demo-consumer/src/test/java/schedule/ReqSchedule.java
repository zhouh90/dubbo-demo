package schedule;

import java.util.HashMap;
import java.util.Map;

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
		Map<String, String> param = new HashMap<String, String>();
		param.put("taskName", "Task1");
		param.put("groupName", "TaskGroup");
		param.put("cronExpression", "*/10 * * * * ?");
		param.put("replace", "true");
//		String result = HttpClientUtil.postSendJson(url, object.toJSONString());
		String result = HttpClientUtil.doPost(url, param, new HashMap<String, String>());
		System.out.println(result);
	}

}
