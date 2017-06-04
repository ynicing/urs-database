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
package com.ursful.framework.database;

import com.ursful.framework.database.page.Pair;

import java.util.List;

public class SQLHelper {
	
	private String sql;
	private List<Pair<Object>> parameters;

	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public List<Pair<Object>> getParameters() {
		return parameters;
	}
	public void setParameters(List<Pair<Object>> parameters) {
		this.parameters = parameters;
	}
	
	public String toString(){
		return sql + " : " + parameters;
	}
	
	
}
