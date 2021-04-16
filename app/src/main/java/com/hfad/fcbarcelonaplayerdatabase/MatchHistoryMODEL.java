package com.hfad.fcbarcelonaplayerdatabase;

import java.util.regex.MatchResult;

public class MatchHistoryMODEL {

    private int id;
    private String UserTeamName;
    private String OpponentTeamName;
    private int UserResult;
    private int OpponentResult;
    private String MatchResult;

    public MatchHistoryMODEL(int id, String userTeamName, String opponentTeamName, int userResult, int opponentResult, String matchResult) {
        this.id = id;
        UserTeamName = userTeamName;
        OpponentTeamName = opponentTeamName;
        UserResult = userResult;
        OpponentResult = opponentResult;
        MatchResult = matchResult;
    }


    @Override
    public String toString() {
        return
                "Match number " + id +
                ": " + UserTeamName + " vs " +
                 OpponentTeamName + " " + UserResult +
                " - " + OpponentResult +
                "  " + MatchResult;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserTeamName() {
        return UserTeamName;
    }

    public void setUserTeamName(String userTeamName) {
        UserTeamName = userTeamName;
    }

    public String getOpponentTeamName() {
        return OpponentTeamName;
    }

    public void setOpponentTeamName(String opponentTeamName) {
        OpponentTeamName = opponentTeamName;
    }

    public int getUserResult() {
        return UserResult;
    }

    public void setUserResult(int userResult) {
        UserResult = userResult;
    }

    public int getOpponentResult() {
        return OpponentResult;
    }

    public void setOpponentResult(int opponentResult) {
        OpponentResult = opponentResult;
    }

    public String getMatchResult() {
        return MatchResult;
    }

    public void setMatchResult(String matchResult) {
        MatchResult = matchResult;
    }
}
