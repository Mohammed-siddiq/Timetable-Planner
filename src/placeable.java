/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;

/**
 *
 * @author AFZAAL
 */
public class placeable implements Serializable{

    //   inputd obji = new inputd();

    private int flag;
    private int c;
    private int subf;

    placeable() {
        flag = 0;
    }

    boolean place(int sub, int day, int p, subject s[], int tt[][]) {
        flag = 0;
        if (day == 5) {
            if (p > 3)// for sat generate till 4th period only
            {
                return false;
            }
        }
        if (tt[day][p] == -1)// if that day(period) is not prioritized or already occupied
        {
            if (s[sub].sc != s[sub].noc)//if sub count is not reached
            {
                if (s[sub].teach[day][p] == 0)//if teacher is free
                {
                    flag = 1;
                }
            }
        }
        if (flag == 1) {
            for (int i = 0; i < 7; i++) {
                if (p == 0) {
                    if (s[sub].teach[day][p + 1] != 0) {
                        return false;
                    }
                } else if (p == 6) {
                    if (s[sub].teach[day][p - 1] != 0) {
                        return false;
                    }
                } else {
                    if ((s[sub].teach[day][p - 1] != 0 || s[sub].teach[day][p + 1] != 0)) {
                        return false;
                    }
                }
            }
            subf = 0;
            for (int i = 0; i < 7; i++) {
                c = 0;
                for (int j = 0; j < 7; j++) {
                    if (i != j) {
                        if (tt[day][i] == tt[day][j]) {
                            c++;
                            if (c == 2) {
                                subf = 1;
                                break;
                            }

                        }
                    }

                }
                if (subf == 1) {
                    break;
                }
            }
            if (subf == 1) {
                for (int i = 0; i < 7; i++) {
                    if (sub == tt[day][i]) {
                        return false;
                    }
                }
            }
            for (int d = 0; d < 6; d++)//uniqueness in column
            {
                if (tt[d][p] == sub) {
                    return false;
                }
            }
            if (p < 4) {
                for (int i = 0; i < 4; i++)//not more than one sub in morning
                {
                    if (tt[day][i] == sub) {
                        return false;
                    }
                }

            } else {
                for (int i = 4; i < 7; i++)//not more than one sub in afternoon
                {
                    if (i == 4) {
                        if (tt[day][i - 1] == sub) {
                            return false;
                        }
                    }
                    if (tt[day][i] == sub) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;

    }
}
