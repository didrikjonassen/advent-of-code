package me.didrik.adventofcode.year2017.day11

class Grid {
    private var n = 0
    private var s = 0
    private var ne = 0
    private var nw = 0
    private var se = 0
    private var sw = 0

    operator fun plusAssign(direction: String) {
        when (direction) {
            "n" -> ++n
            "s" -> ++s
            "ne" -> ++ne
            "nw" -> ++nw
            "se" -> ++se
            "sw" -> ++sw
        }
        while(reduce());
    }

    private fun reduce(): Boolean {
        return when {
            n > 0 && s >0       -> {--n; --s; true}
            ne > 0 && sw > 0    -> {--ne; --sw; true}
            nw > 0 && se > 0    -> {--nw; --se; true}
            n > 0 && se > 0     -> {--n; --se; ++ne; true}
            n > 0 && sw > 0     -> {--n; --sw; ++nw; true}
            s > 0 && ne > 0     -> {--s; --ne; ++se; true}
            s > 0 && nw > 0     -> {--s; --nw; ++sw; true}
            sw > 0 && se > 0    -> {--sw; --se; ++s; true}
            nw > 0 && ne > 0    -> {--nw; --ne; ++n; true}
            else                -> false
        }
    }

    fun distance() = n+s+ne+nw+se+sw
}