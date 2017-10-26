/*
 * Copyright 2017 @ursful.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.weitu.framework.component.orm.helper;

import com.weitu.framework.component.orm.support.Pair;
import org.springframework.validation.DataBinder;

import java.lang.reflect.Field;
import java.util.List;

public class SQLHelper {

	public Field getIdField() {
		return idField;
	}

	public void setIdField(Field idField) {
		this.idField = idField;
	}

	private Field idField;

	public void setId(Object object, Object value){
		if(value == null || object == null){
			return;
		}
		try {
			idField.setAccessible(true);
			DataBinder binder = new DataBinder(idField, idField.getName());
			Object result = binder.convertIfNecessary(value.toString(), idField.getType());
			idField.set(object, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String sql;
	private List<Pair> parameters;

	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public List<Pair> getParameters() {
		return parameters;
	}
	public void setParameters(List<Pair> parameters) {
		this.parameters = parameters;
	}
	
	public String toString(){
		return "\n**********************************************************\nSQL: " + sql + "\nPARAMETERS: " +
                parameters+"\n**********************************************************\n";
	}
	
	
}
