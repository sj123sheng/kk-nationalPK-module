package com.melot.kk.nationalPK.api.domain.DO;

import java.io.Serializable;
import java.util.List;

public class ConfLadderMatchPageDO implements Serializable{

    private static final long serialVersionUID = 8352083186416558285L;

    private Integer totalCount;

    private List<ConfLadderMatchDO> confLadderMatchDOS;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<ConfLadderMatchDO> getConfLadderMatchDOS() {
        return confLadderMatchDOS;
    }

    public void setConfLadderMatchDOS(List<ConfLadderMatchDO> confLadderMatchDOS) {
        this.confLadderMatchDOS = confLadderMatchDOS;
    }
}
