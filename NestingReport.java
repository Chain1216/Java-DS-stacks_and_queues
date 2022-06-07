import java.util.Objects;
import java.util.Stack;

/**
 * NestingReport indicates whether the input was correctly nested (valid) and
 * additional state to provide details when the input is not valid.
 */
public class NestingReport {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 679233489923471L;

    public enum Status {
        VALID, NULL_INPUT, NULL_ITEM, INVALID_CLOSE, NOT_TERMINATED
    }

    private final Status status;
    private final Nestable badItem; // nullable
    private final Stack<? extends Nestable> stackState; // nullable

    public NestingReport(Status status, Nestable badItem, Stack<? extends Nestable> stackState) {
        this.status = status;
        this.badItem = badItem;
        this.stackState = stackState;
    }

    public boolean isValid() {
        return status == Status.VALID;
    }

    public Status getStatus() {
        return status;
    }

    public Nestable getBadItem() {
        return badItem;
    }

    public Stack<? extends Nestable> getStackState() {
        return stackState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NestingReport that = (NestingReport) o;
        return status == that.status && Objects.equals(badItem, that.badItem) && Objects.equals(stackState, that.stackState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, badItem, stackState);
    }

    @Override
    public String toString() {
        return "NestingReport{" +
                "status=" + status +
                ", badItem=" + badItem +
                ", stackState=" + stackState +
                '}';
    }
}
