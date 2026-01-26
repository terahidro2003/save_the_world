package com.skarbalius;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = Parameters.class)
public abstract class Parameters_T
{
    public double LENGTH1;
    public double RADIUS1;
    public double EPSILON;
    public double AREA1;
    public int Q_PTS;
    public int N_PTS;
    public int K_PTS;
    public int A_PTS;
    public int B_PTS;
    public int C_PTS;
    public int D_PTS;
    public int E_PTS;
    public int F_PTS;
    public int G_PTS;
    public double LENGTH2;
    public double RADIUS2;
    public double AREA2;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        Parameters_T that = (Parameters_T) obj;

        if (Double.compare(that.LENGTH1, LENGTH1) != 0) return false;
        if (Double.compare(that.RADIUS1, RADIUS1) != 0) return false;
        if (Double.compare(that.EPSILON, EPSILON) != 0) return false;
        if (Double.compare(that.AREA1, AREA1) != 0) return false;
        if (Q_PTS != that.Q_PTS) return false;
        if (N_PTS != that.N_PTS) return false;
        if (K_PTS != that.K_PTS) return false;
        if (A_PTS != that.A_PTS) return false;
        if (B_PTS != that.B_PTS) return false;
        if (C_PTS != that.C_PTS) return false;
        if (D_PTS != that.D_PTS) return false;
        if (E_PTS != that.E_PTS) return false;
        if (F_PTS != that.F_PTS) return false;
        if (G_PTS != that.G_PTS) return false;
        if (Double.compare(that.LENGTH2, LENGTH2) != 0) return false;
        if (Double.compare(that.RADIUS2, RADIUS2) != 0) return false;
        return Double.compare(that.AREA2, AREA2) == 0;
    }
}