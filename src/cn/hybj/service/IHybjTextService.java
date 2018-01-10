package cn.hybj.service;

import java.util.List;

import cn.hybj.domain.HybjText;
import cn.hybj.web.form.HybjTextForm;

public interface IHybjTextService {
	public final static String SERVICE_NAME = "cn.hybj.service.impl.HybjTextServiceImpl";
	public void saveElecText(HybjText elecText);
	public List<HybjText> findCollectionByConditionNoPage(
			HybjTextForm hybjTextForm);
}
