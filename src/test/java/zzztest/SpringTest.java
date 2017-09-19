package zzztest;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fusio.tag.commons.tools.DataImporter;
import com.fusio.tag.commons.utils.DateUtil;
import com.fusio.tag.dao.DaoUtil;
import com.fusio.tag.dao.autogen.CatgMapper;
import com.fusio.tag.service.CatgServiceI;
import com.fusio.tag.service.DataImportServiceI;
import com.fusio.tag.service.TagServiceI;
import com.fusio.tag.service.TgTagServiceI;
import com.fusio.tag.service.cmm.CommonServiceI;
import com.fusio.tag.vo.AllGraph;
import com.fusio.tag.vo.IdOrCode;

public class SpringTest extends BaseJunitTest {
	@Resource
	private DaoUtil daoUtil;
	@Resource
	private DataImportServiceI dataImportServiceI;
	@Resource
	private TgTagServiceI tgTagServiceI;
	@Resource
	private CommonServiceI commonServiceI;
	@Resource
	private TagServiceI tagServiceI;
	@Resource
	private CatgServiceI catgServiceI;

	
	// 初始化tags表和catg表
	@Test
	public void initTagsAndCatgs() throws Exception {
		CatgMapper mapper = daoUtil.getMapper(CatgMapper.class);
		String sourceFilePath = "d:/郑老师第四次标签_dmp_tag_system_table v0.4 (20170206）.txt";
		
		boolean isRealInsert = true;
		dataImportServiceI.saveCatgAndTags(sourceFilePath, isRealInsert);
		dataImportServiceI.maintainTag_pid();
		dataImportServiceI.maintainTag_CatgId();
		dataImportServiceI.maintainCatg_pid();

	}
	
	
	
	// 获得category的树图
	@Test
	public void getCategoryTreeGraph() throws Exception {
		AllGraph full = catgServiceI.qryFullGraph();
		this.println(full);

	}
	
	
	
	
	
	
	
	@Test
	public void getWellData() throws Exception {
		String sourceFilePath = "d:/第二次补充标签.txt";
		String destFilePath = sourceFilePath + "_" + DateUtil.getCurTimestampStr() + "_去重和补全";
		DataImporter.wellData(sourceFilePath, destFilePath);
	}

	@Test
	public void tttttttt() throws Exception {
		IdOrCode idOrCode = new IdOrCode();
		idOrCode.setId("00223d4f9fc746179b3e3b2b079e1357");

		this.println(tagServiceI.qryAllParentTags(idOrCode));

	}

	private static Logger logger = LoggerFactory.getLogger(SpringTest.class);

}
