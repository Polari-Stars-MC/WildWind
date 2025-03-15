package org.polaris2023.wild_wind.util;

import java.util.Random;

public final class RandomUtil {
	public static class GaussianMixture2D {
		private final double[][] pdf;
		private final int length;

		private GaussianMixture2D(double[][] pdf, int length) {
			this.pdf = pdf;
			this.length = length;
		}
		public GaussianMixture2D(long seed, int length, int mix, double minSig, double maxSig) {
			this(buildPDF(seed, length, mix, minSig, maxSig), length);
		}

		private static void addGaussian(double[][] destination, int length, double mu1, double mu2, double sig1, double sig2, double cov) {
			double det = sig1 * sig2 - cov * cov;
			for(int i = 0; i < length; ++i) {
				double x = i - mu1;
				for(int j = 0; j < length; ++j) {
					double y = j - mu2;
					destination[i][j] += Math.pow(2.0D * Math.PI, -1.0D) * Math.pow(det, -0.5D) * Math.exp(
							-0.5D * (x * x * sig2 - 2.0D * x * y * cov + y * y * sig1) / det
					);
				}
			}
		}

		private static final double MAX_R = 0.8D;
		private static double[][] buildPDF(long seed, int length, int mix, double minSig, double maxSig) {
			double[][] pdf = new double[length][length];
			Random random = new Random(seed);
			double mu1 = random.nextDouble() * length * 0.75D + length * 0.125D;
			double mu2 = random.nextDouble() * length * 0.75D + length * 0.125D;
			for(int i = 0; i < length; ++i) {
				double x = i - mu1;
				for(int j = 0; j < length; ++j) {
					double y = j - mu2;
					pdf[i][j] = Math.pow(2.0D * Math.PI, -1.0D) / 5.0D * Math.exp(-0.05D * (x * x + y * y));
				}
			}
			for(int ignored = 0; ignored < mix; ++ignored) {
				double sig1 = random.nextDouble() * (maxSig - minSig) + minSig;
				double sig2 = random.nextDouble() * (maxSig - minSig) + minSig;
				double bound = Math.pow(sig1 * sig2, 0.5D);
				addGaussian(
						pdf, length,
						random.nextDouble() * length * 0.6D + length * 0.2D,
						random.nextDouble() * length * 0.6D + length * 0.2D,
						sig1, sig2, (random.nextDouble() * MAX_R * 2.0D - MAX_R) * bound
				);
			}

			return pdf;
		}

		public double get(int x, int y) {
			if(x < 0 || x >= this.length) {
				throw new IndexOutOfBoundsException("Cannot get pdf at x = " + x + ", expected 0 <= x < " + this.length + ".");
			}
			if(y < 0 || y >= this.length) {
				throw new IndexOutOfBoundsException("Cannot get pdf at y = " + y + ", expected 0 <= y < " + this.length + ".");
			}
			return this.pdf[x][y];
		}
	}

	private RandomUtil() {
	}
}
