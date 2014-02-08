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
package net.hasor.plugins.transaction.support;
import java.sql.Savepoint;
import net.hasor.jdbc.datasource.SavepointManager;
import net.hasor.jdbc.exceptions.IllegalTransactionStateException;
import net.hasor.jdbc.exceptions.TransactionSuspensionNotSupportedException;
import net.hasor.plugins.transaction.TransactionBehavior;
import net.hasor.plugins.transaction.TransactionLevel;
import net.hasor.plugins.transaction.TransactionStatus;
/**
 * ��ʾһ�����ڹ��������״̬��
 * @version : 2013-10-30
 * @author ������(zyc@hasor.net)
 */
public class DefaultTransactionStatus implements TransactionStatus {
    private Savepoint           savepoint     = null; //���񱣴��
    private TransactionObject   tranConn      = null; //��ǰ����ʹ�õ����ݿ�����
    private TransactionObject   suspendConn   = null; //��ǰ����֮ǰ�������һ�����ݿ�����
    private TransactionBehavior behavior      = null; //��������
    private TransactionLevel    level         = null; //���뼶��
    private boolean             completed     = false; //��ɣ�true��ʾ��ɣ�
    private boolean             rollbackOnly  = false; //Ҫ��ع���true��ʾ�ع���
    private boolean             newConnection = false; //�Ƿ�ʹ����һ��ȫ�µ����ݿ����ӿ�������true��ʾ�����ӣ�
    private boolean             readOnly      = false; //ֻ��ģʽ��true��ʾֻ����
    //
    public DefaultTransactionStatus(TransactionBehavior behavior, TransactionLevel level, TransactionObject tranConn) {
        this.behavior = behavior;
        this.level = level;
        this.tranConn = tranConn;
    }
    //
    //
    //
    private SavepointManager getSavepointManager() {
        return this.tranConn.getSavepointManager();
    }
    public void markHeldSavepoint() {
        if (this.hasSavepoint())
            throw new IllegalTransactionStateException("TransactionStatus has Savepoint");
        if (this.getSavepointManager().supportSavepoint() == false)
            throw new TransactionSuspensionNotSupportedException("SavepointManager does not support Savepoint.");
        //
        this.savepoint = this.getSavepointManager().createSavepoint();
    }
    public void releaseHeldSavepoint() {
        if (this.hasSavepoint() == false)
            throw new IllegalTransactionStateException("TransactionStatus has not Savepoint");
        if (this.getSavepointManager().supportSavepoint() == false)
            throw new TransactionSuspensionNotSupportedException("SavepointManager does not support Savepoint.");
        //
        this.getSavepointManager().releaseSavepoint(this.savepoint);
    }
    public void rollbackToHeldSavepoint() {
        if (this.hasSavepoint() == false)
            throw new IllegalTransactionStateException("TransactionStatus has not Savepoint");
        if (this.getSavepointManager().supportSavepoint() == false)
            throw new TransactionSuspensionNotSupportedException("SavepointManager does not support Savepoint.");
        //
        this.getSavepointManager().rollbackToSavepoint(this.savepoint);
    }
    /*�������״̬*/
    void setCompleted() {
        this.completed = true;
    }
    /*���ʹ�õ���ȫ������*/
    void markNewConnection() {
        this.newConnection = true;
    }
    TransactionObject getTranConn() {
        return tranConn;
    }
    void setTranConn(TransactionObject tranConn) {
        this.tranConn = tranConn;
    }
    TransactionObject getSuspendConn() {
        return suspendConn;
    }
    void setSuspendConn(TransactionObject suspendConn) {
        this.suspendConn = suspendConn;
    }
    //
    //
    //
    public TransactionBehavior getTransactionBehavior() {
        return this.behavior;
    }
    public TransactionLevel getIsolationLevel() {
        return this.level;
    }
    public boolean isCompleted() {
        return this.completed;
    }
    public boolean isRollbackOnly() {
        return this.rollbackOnly;
    }
    public boolean isReadOnly() {
        return this.readOnly;
    }
    public boolean isNewConnection() {
        return this.newConnection;
    }
    public boolean isSuspend() {
        return (this.suspendConn != null) ? true : false;
    }
    public boolean hasSavepoint() {
        return (this.savepoint != null) ? true : false;
    }
    public void setRollbackOnly() {
        if (this.isCompleted())
            throw new IllegalTransactionStateException("Transaction is already completed.");
        this.rollbackOnly = true;
    }
    public void setReadOnly() {
        if (this.isCompleted())
            throw new IllegalTransactionStateException("Transaction is already completed.");
        this.readOnly = true;
    }
}