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
		public GaussianMixture2D(long seed, int length, int mix) {
			this(buildPDF(seed, length, mix), length);
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

		private static final double MIN_SIG = 2.0D;
		private static final double MAX_SIG = 8.0D;
		private static final double MAX_R = 0.8D;
		private static double[][] buildPDF(long seed, int length, int mix) {
			double[][] pdf = new double[length][length];
			for(int i = 0; i < length; ++i) {
				for(int j = 0; j < length; ++j) {
					pdf[i][j] = Math.pow(2.0D * Math.PI, -1.0D) / 5.0D * Math.exp(-0.05D * (i * i + j * j));
				}
			}
			Random random = new Random(seed);
			for(int ignored = 0; ignored < mix; ++ignored) {
				double sig1 = random.nextDouble() * (MAX_SIG - MIN_SIG) + MIN_SIG;
				double sig2 = random.nextDouble() * (MAX_SIG - MIN_SIG) + MIN_SIG;
				double bound = Math.pow(sig1 * sig2, 0.5D);
				addGaussian(
						pdf, length,
						random.nextDouble() * length, random.nextDouble() * length,
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
