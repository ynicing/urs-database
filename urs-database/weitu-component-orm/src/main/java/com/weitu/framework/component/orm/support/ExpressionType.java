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

package com.weitu.framework.component.orm.support;
public enum ExpressionType {
    CDT_Equal, //=
    CDT_NotEqual,//<> !=
    CDT_Like,// like %x% 不建议使用
    CDT_LikeRight,// x% 建议使用
    CDT_LikeLeft,// %x  禁止使用 除非特定情况
    CDT_Less,// <
    CDT_More,// >
    CDT_LessEqual,// <=
    CDT_MoreEqual,// >=
    CDT_In, // []/List/Collection/Set
    CDT_NotLike// _a ...
}