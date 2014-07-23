/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.application;

import com.dubic.module.el.LogActionDelegate;
import com.dubic.module.el.data.Log;
import com.dubic.scribbles.db.Database;
import com.dubic.scribbles.utils.IdmUtils;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.helpers.LogLog;

/**
 *
 * @author dubem
 */
@Named
public class LogDelegateImpl implements LogActionDelegate {

    @Inject
    private Database db;

    @Override
    public void logAction(Log log) {
        try {
            //        System.out.println(log);
            logRepository(log);
        } catch (Exception ex) {
            LogLog.warn(ex.getMessage(), ex);
        }
    }

    public void logRepository(Log log) throws Exception {
        LogModel newLog = new LogModel(log);
        newLog.setUsername(IdmUtils.getUserEmailLoggedIn());
        LogModel oldLog = getLastLog(log.getClassName(), log.getMethod(), log.getLineNo());
        if (oldLog != null) {
            oldLog.addOccurrence(1);
            db.merge(oldLog);
            LogLog.debug("Last DB log saved...");
        } else {
            db.persist(newLog);
            LogLog.debug("New DB log saved...");
        }

    }

    private LogModel getLastLog(String cls, String method, String lineNo) {
        try {
            List<LogModel> logList = db.namedQuery("log.exists", LogModel.class)
                    .setParameter("cls", cls).setParameter("mtd", method).setParameter("lnum", lineNo)
                    .getResultList();
            return IdmUtils.getFirstOrNull(logList);
        } catch (Exception e) {
            LogLog.warn("SmartLogDelegate exception - " + e.getMessage(), e);
            return null;
        }
    }

}
