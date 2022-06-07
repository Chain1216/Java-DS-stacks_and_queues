/*
 * Author: Rafi Rubin
 */
public abstract class Nestable {
    @SuppressWarnings("unused")
    long serialVersionUID = 294891280812308L;

    public enum NestEffect {
        OPEN, NEUTRAL, CLOSE;

        public boolean matches(NestEffect e) {
            switch (e) {
                case OPEN:
                    return this == CLOSE;
                case CLOSE:
                    return this == OPEN;
                default:
                    return true;
            }
        }
    }

    protected final NestEffect effect;

    public Nestable(NestEffect effect) {
        this.effect = effect;
    }

    public NestEffect getEffect() {
        return this.effect;
    }

    public abstract boolean matches(Nestable other);
}
