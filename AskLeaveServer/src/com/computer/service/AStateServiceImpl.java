package com.computer.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.computer.entity.Response;
import com.computer.entity.AState;
import com.computer.util.Log;

@Service
public class AStateServiceImpl extends BaseService implements AStateService {
	private static final Class<?> stateClass = AState.class;
	protected Log log = Log.getLog(stateClass);

	@Override
	public Response<List<AState>> queryAllState() {
		Response<List<AState>> resp=new Response<List<AState>>();
		List<AState> mstate = (List<AState>) db.findAll(stateClass);
		if (mstate == null) {
			log.i("查询所有state失败");
			resp.setDescription("服务器异常，无法新增，请稍后再试");
			resp.setOperateResult(false);
			 
		}else {
			resp.setDescription("查询所有状态成功");
			resp.setOperateResult(true);
			resp.setObject(mstate);
		}
		return resp;
	}

	@Override
	public Response<String> queryState(Integer id) {
		Response<String> resp = new Response<String>();
		AState mstate = (AState) db.findById(stateClass, id);
		if (mstate == null) {
			log.i("state表不存在id="+id);
			resp.setDescription("state表不存在id="+id);
			resp.setOperateResult(false);
			 
		}else {
			resp.setDescription("查询一个状态成功");
			resp.setOperateResult(true);
			resp.setObject(mstate.getStates());
		}
		return resp;
	}

	@Override
	public Response<Boolean> delState(Integer id) {
		Response<Boolean> resp = new Response<Boolean>();
		AState mstate = (AState) db.findById(stateClass, id);
		if (mstate == null) {
			log.i("state表不存在id");
			resp.setDescription("state表不存在id");
			resp.setOperateResult(false);
			return resp;
		}
		Boolean ret=db.deleteById(stateClass, id)>0;
		if(ret)resp.setDescription("删除"+id+"状态成功");
		else{
			log.i("删除状态 失败");
			resp.setDescription("删除"+id+"状态 失败");
		}
		resp.setOperateResult(ret);
		return resp;
	}

	@Override
	public Response<AState> addState(String states) {
		Response<AState> resp = new Response<AState>();
		AState mstate = (AState) db.findByName(AState.class, states);
		if (mstate != null) {
			log.i("状态已存在，无法新增");
			resp.setDescription("状态已存在，无法添加");
			resp.setOperateResult(false);
			return resp;
		} else
			mstate = new AState(states);

		mstate = (AState) db.insert(mstate);
		if (mstate == null) {
			log.i("数据库<state表>插入失败");
			resp.setDescription("服务器异常，无法新增，请稍后再试");
			resp.setOperateResult(false);
		} else {
			resp.setDescription("新增状态成功");
			resp.setOperateResult(true);
			resp.setObject(mstate);
		}
		return resp;
	}

}
