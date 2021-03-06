/*
 * Copyright 2008-2009 the original ������(zyc@hasor.net).
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
package org.more.webui;
/**
 * ��ʧ�쳣��
 * @version 2009-4-29
 * @author ������ (zyc@byshell.org)
 */
public class LostException extends RuntimeException {
    private static final long serialVersionUID = 3664651649094973500L;
    /**��ʧ�쳣*/
    public LostException(String string) {
        super(string);
    }
    /**��ʧ�쳣*/
    public LostException(Throwable error) {
        super(error);
    }
    /**��ʧ�쳣*/
    public LostException(String string, Throwable error) {
        super(string, error);
    }
}