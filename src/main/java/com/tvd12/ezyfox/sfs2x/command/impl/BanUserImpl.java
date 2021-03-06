package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.managers.BanMode;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.BanUser;
import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see BanUser
 * 
 * @author tavandung12
 * Created on May 26, 2016
 *
 */
public class BanUserImpl extends BaseCommandImpl implements BanUser {

    private String message;
    private boolean bandByAddressMode;
    private int durationMinutes;
    private int delaySeconds;
    private String modUser = "";
    private String userToBan = "";
    
    
    /**
     * @param context the context
     * @param api the api
     * @param extension the extension
     */
    public BanUserImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BaseCommand#execute()
     */
    @SuppressWarnings("unchecked")
    @Override
    public Boolean execute() {
        api.banUser(CommandUtil.getSfsUser(userToBan, api), 
                CommandUtil.getSfsUser(modUser, api), 
                message, 
                bandByAddressMode ? BanMode.BY_ADDRESS : BanMode.BY_NAME, 
                durationMinutes, 
                delaySeconds);
        return Boolean.TRUE;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BanUser#user(com.tvd12.ezyfox.core.entities.ApiBaseUser)
     */
    @Override
    public BanUser user(ApiBaseUser userToBan) {
        this.userToBan = userToBan.getName();
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BanUser#user(java.lang.String)
     */
    @Override
    public BanUser user(String userToBan) {
        this.userToBan = userToBan;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BanUser#modUser(java.lang.String)
     */
    @Override
    public BanUser modUser(String modUser) {
        this.modUser = modUser;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BanUser#message(java.lang.String)
     */
    @Override
    public BanUser message(String banMessage) {
        this.message = banMessage;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BanUser#byAddress()
     */
    @Override
    public BanUser byAddress() {
        this.bandByAddressMode = true;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BanUser#byName()
     */
    @Override
    public BanUser byName() {
        this.bandByAddressMode = false;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BanUser#duration(int)
     */
    @Override
    public BanUser duration(int durationMinutes) {
        this.durationMinutes = durationMinutes;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BanUser#delay(int)
     */
    @Override
    public BanUser delay(int delaySeconds) {
        this.delaySeconds = delaySeconds;
        return this;
    }

}
