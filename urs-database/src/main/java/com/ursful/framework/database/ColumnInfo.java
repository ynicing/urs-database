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

import com.ursful.framework.database.data.DataType;

public class ColumnInfo {
	
	private String name;//test.userId
	private String columnName;//user_id
	private DataType dataType;//String
	private Boolean unique;//pk?
	private String largeString;//clob?
	
	public String getLargeString() {
		return largeString;
	}
	public void setLargeString(String largeString) {
		this.largeString = largeString;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public DataType getDataType() {
		return dataType;
	}
	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}
	public Boolean getUnique() {
		return unique;
	}
	public void setUnique(Boolean unique) {
		this.unique = unique;
	}

	public String toString(){
		return name + "(" + columnName + ",pk:" + unique + ")" + (dataType != null? dataType.name():"null");
	}
}
