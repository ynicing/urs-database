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
package com.weitu.framework.component.orm;


import com.weitu.framework.component.orm.exception.QueryException;
import com.weitu.framework.component.orm.support.*;
import com.weitu.framework.component.orm.support.Terms;

public interface IBaseQuery extends IQuery{

    //IBaseQuery createDistinctQuery(Class<?> clazz, Column... columns) throws QueryException;
    //IBaseQuery createQuery(Class<?> clazz, Column... columns) throws QueryException;//select a.id, a.name from
    //IBaseQuery createCount() throws QueryException;;//select count(*) from.
    //IBaseQuery createCount(Column column) throws QueryException;//select count(a.id) from...
    //IBaseQuery createDistinctString(Column column) throws QueryException;
    //IBaseQuery createPage(Page page) throws QueryException;// page...
    //QueryInfo getQueryInfo();

	//从class中获取字段，该字段可有可无
	IBaseQuery table(Class<?> clazz) throws QueryException;
	IBaseQuery where(String name, Object value, ExpressionType type);
	IBaseQuery where(Terms terms);//select * from test where (a = ? or b = ? ...)
	IBaseQuery group(String name);
	IBaseQuery having(String name, Object value, ExpressionType type);
	IBaseQuery having(Terms terms);//group by  having (a = ? or b = ? ...)
	IBaseQuery orderDesc(String name);
	IBaseQuery orderAsc(String name);
    IBaseQuery createQuery() throws QueryException;//default table clazz
	IBaseQuery createQuery(String... names) throws QueryException;
	IBaseQuery createQuery(Column... columns) throws QueryException;
    IBaseQuery distinct();


}
