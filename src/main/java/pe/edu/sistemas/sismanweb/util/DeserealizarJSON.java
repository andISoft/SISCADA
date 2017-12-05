package pe.edu.sistemas.sismanweb.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DeserealizarJSON<Model> {
	
	protected final Log logger = LogFactory.getLog(DeserealizarJSON.class);
	public static final ObjectMapper JSON_MAPPER = new ObjectMapper();
	
	private Class<Model> modelClass;
	private Model model;

	public DeserealizarJSON(Class<Model> modelClass){
		this.modelClass = modelClass;
		model = null;
	}
	
	
	
	public List<Model> deserealiza(JSONArray jsonArray){
		JSONObject jsonObject = null;
		List<Model> listModel = new ArrayList<Model>();
		for(int i=0; i< jsonArray.length(); i++){
			jsonObject = jsonArray.getJSONObject(i);
			try {
				model = JSON_MAPPER.readValue(jsonObject.toString(), modelClass);
				logger.info("Objecto json: "+jsonObject.toString());
				listModel.add(model);
			} catch (Exception e) {
				logger.error("OCURRIO UN ERROR EN EL REGISTRO: "+(i+1));
				e.printStackTrace();
				return listModel;
			}
		}			
		return listModel;
	}

}
