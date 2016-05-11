package road.signs.recognizer.config.training;

import org.encog.ml.data.MLDataSet;
import org.encog.neural.networks.ContainsFlat;
import org.encog.neural.networks.training.propagation.Propagation;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import org.encog.neural.networks.training.propagation.manhattan.ManhattanPropagation;
import org.encog.neural.networks.training.propagation.quick.QuickPropagation;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.neural.networks.training.propagation.scg.ScaledConjugateGradient;

/**
 * Created by Basia on 11.05.16.
 */
public enum PropagationType {

    ResilientPropagation {
        @Override
        public Propagation getInstance(ContainsFlat network, MLDataSet training, double theLearnRateForManhattanPropagation) {
            return new ResilientPropagation(network, training);
        }
    },
    ManhattanPropagation {
        @Override
        public Propagation getInstance(ContainsFlat network, MLDataSet training, double theLearnRateForManhattanPropagation) {
            return new ManhattanPropagation(network, training, theLearnRateForManhattanPropagation);
        }
    },
    Backpropagation {
        @Override
        public Propagation getInstance(ContainsFlat network, MLDataSet training, double theLearnRateForManhattanPropagation) {
            return new Backpropagation(network, training);
        }
    },
    ScaledConjugateGradient {
        @Override
        public Propagation getInstance(ContainsFlat network, MLDataSet training, double theLearnRateForManhattanPropagation) {
            return new ScaledConjugateGradient(network, training);
        }
    },
    QuickPropagation {
        @Override
        public Propagation getInstance(ContainsFlat network, MLDataSet training, double theLearnRateForManhattanPropagation) {
            return new QuickPropagation(network, training);
        }
    };

    public abstract Propagation getInstance(ContainsFlat network, MLDataSet training, double theLearnRateForManhattanPropagation);

    }
