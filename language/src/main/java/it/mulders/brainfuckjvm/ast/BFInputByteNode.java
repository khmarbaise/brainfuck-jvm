package it.mulders.brainfuckjvm.ast;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.source.SourceSection;
import it.mulders.brainfuckjvm.BrainfuckContext;
import it.mulders.brainfuckjvm.BrainfuckLanguage;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Represents the "Input Byte" command.
 */
public class BFInputByteNode extends BFCommandNode {

    public BFInputByteNode(final SourceSection sourceSection, final FrameSlot dataPointerSlot) {
        super(sourceSection, dataPointerSlot);
    }

    @Override
    public void execute(final VirtualFrame frame) {
        final byte currentValue = doRead(getContext().getInput());
        frame.setByte(getSlot(frame), currentValue);
    }

    private BrainfuckContext getContext() {
        return getRootNode().getLanguage(BrainfuckLanguage.class).getContextReference().get();
    }

    @TruffleBoundary
    @SuppressWarnings("squid:S106")
    private static byte doRead(final BufferedReader in) {
        final char[] chars = new char[1];
        try {
            final int bytesRead = in.read(chars, 0, 1);
            if (bytesRead == -1) {
                System.err.println("Could not read char from standard input: no input left");
            }
        } catch (IOException ioe) {
            System.err.println("Could not read char from standard input");
        }
        return (byte) chars[0];
    }

    @Override
    public String toString() {
        return "INPUT";
    }
}
