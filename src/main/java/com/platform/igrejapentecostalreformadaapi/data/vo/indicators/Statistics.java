package com.platform.igrejapentecostalreformadaapi.data.vo.indicators;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Statistics implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int totalMembers;

    private int totalMembersWithRegisterCompleted;

    private int totalMembersFemale;

    private int totalMembersMale;

    private int totalMembersInCampinas;

    private int totalMembersAdmin;

    public Statistics() {
    }

    public Statistics(int totalMembers, int totalMembersWithRegisterCompleted, int totalMembersFemale, int totalMembersMale, int totalMembersInCampinas, int totalMembersAdmin) {
        this.totalMembers = totalMembers;
        this.totalMembersWithRegisterCompleted = totalMembersWithRegisterCompleted;
        this.totalMembersFemale = totalMembersFemale;
        this.totalMembersMale = totalMembersMale;
        this.totalMembersInCampinas = totalMembersInCampinas;
        this.totalMembersAdmin = totalMembersAdmin;
    }

    public int getTotalMembers() {
        return totalMembers;
    }

    public void setTotalMembers(int totalMembers) {
        this.totalMembers = totalMembers;
    }

    public int getTotalMembersWithRegisterCompleted() {
        return totalMembersWithRegisterCompleted;
    }

    public void setTotalMembersWithRegisterCompleted(int totalMembersWithRegisterCompleted) {
        this.totalMembersWithRegisterCompleted = totalMembersWithRegisterCompleted;
    }

    public int getTotalMembersFemale() {
        return totalMembersFemale;
    }

    public void setTotalMembersFemale(int totalMembersFemale) {
        this.totalMembersFemale = totalMembersFemale;
    }

    public int getTotalMembersMale() {
        return totalMembersMale;
    }

    public void setTotalMembersMale(int totalMembersMale) {
        this.totalMembersMale = totalMembersMale;
    }

    public int getTotalMembersInCampinas() {
        return totalMembersInCampinas;
    }

    public void setTotalMembersInCampinas(int totalMembersInCampinas) {
        this.totalMembersInCampinas = totalMembersInCampinas;
    }

    public int getTotalMembersAdmin() {
        return totalMembersAdmin;
    }

    public void setTotalMembersAdmin(int totalMembersAdmin) {
        this.totalMembersAdmin = totalMembersAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Statistics that)) return false;
        return getTotalMembers() == that.getTotalMembers() && getTotalMembersWithRegisterCompleted() == that.getTotalMembersWithRegisterCompleted() && getTotalMembersFemale() == that.getTotalMembersFemale() && getTotalMembersMale() == that.getTotalMembersMale() && getTotalMembersInCampinas() == that.getTotalMembersInCampinas() && getTotalMembersAdmin() == that.getTotalMembersAdmin();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTotalMembers(), getTotalMembersWithRegisterCompleted(), getTotalMembersFemale(), getTotalMembersMale(), getTotalMembersInCampinas(), getTotalMembersAdmin());
    }
}
