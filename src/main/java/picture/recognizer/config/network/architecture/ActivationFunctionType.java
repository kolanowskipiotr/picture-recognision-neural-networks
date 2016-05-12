package picture.recognizer.config.network.architecture;

import org.encog.engine.network.activation.*;

/**
 * Created by Basia on 11.05.16.
 */
public enum ActivationFunctionType {

    ActivationSigmoid {
        @Override
        public ActivationFunction getInstance() {
            return new ActivationSigmoid();
        }
    },
    ActivationTANH {
        @Override
        public ActivationFunction getInstance() {
            return new ActivationTANH();
        }
    },
    ActivationSoftMax {
        @Override
        public ActivationFunction getInstance() {
            return new ActivationSoftMax();
        }
    },
    ActivationCompetitive {
        @Override
        public ActivationFunction getInstance() {
            return new ActivationCompetitive();
        }
    },
    ActivationSteepenedSigmoid {
        @Override
        public ActivationFunction getInstance() {
            return new ActivationSteepenedSigmoid();
        }
    },
    ActivationRamp {
        @Override
        public ActivationFunction getInstance() {
            return new ActivationRamp();
        }
    },
    ActivationElliott {
        @Override
        public ActivationFunction getInstance() {
            return new ActivationElliott();
        }
    },
    ActivationLOG {
        @Override
        public ActivationFunction getInstance() {
            return new ActivationLOG();
        }
    },
    ActivationBiPolar {
        @Override
        public ActivationFunction getInstance() {
            return new ActivationBiPolar();
        }
    },
    ActivationStep {
        @Override
        public ActivationFunction getInstance() {
            return new ActivationStep();
        }
    },
    ActivationClippedLinear {
        @Override
        public ActivationFunction getInstance() {
            return new ActivationClippedLinear();
        }
    },
    ActivationElliottSymmetric {
        @Override
        public ActivationFunction getInstance() {
            return new ActivationElliottSymmetric();
        }
    },
    ActivationBipolarSteepenedSigmoid {
        @Override
        public ActivationFunction getInstance() {
            return new ActivationBipolarSteepenedSigmoid();
        }
    },
    ActivationSIN {
        @Override
        public ActivationFunction getInstance() {
            return new ActivationSIN();
        }
    },
    ActivationLinear {
        @Override
        public ActivationFunction getInstance() {
            return new ActivationLinear();
        }
    },
    ActivationGaussian {
        @Override
        public ActivationFunction getInstance() {
            return new ActivationGaussian();
        }
    };

    public abstract ActivationFunction getInstance();
}
