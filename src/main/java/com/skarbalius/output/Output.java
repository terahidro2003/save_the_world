package com.skarbalius.output;

import java.util.Vector;

public record Output(Vector<Boolean> cmv,  Vector<Vector<Boolean>> pum, Vector<Boolean> fuv, Boolean decision) {
}
