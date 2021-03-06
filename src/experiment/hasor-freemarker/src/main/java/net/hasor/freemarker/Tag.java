/*
 * Copyright 2008-2009 the original 赵永春(zyc@hasor.net).
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
package net.hasor.freemarker;
import java.io.IOException;
import freemarker.core.Environment;
import freemarker.template.TemplateException;
/***
 * 自定义标签
 * @version : 2013-5-14
 * @author 赵永春 (zyc@byshell.org)
 */
public interface Tag {
    /**准备开始执行标签*/
    public boolean beforeTag(Environment environment) throws TemplateException;
    /**执行标签*/
    public void doTag(TemplateBody body) throws TemplateException, IOException;
    /**标签执行完毕*/
    public boolean afterTag(Environment environment) throws TemplateException;
}