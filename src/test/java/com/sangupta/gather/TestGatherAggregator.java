package com.sangupta.gather;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import com.sangupta.gather.GatherAggregator.CountingAggregator;
import com.sangupta.gather.GatherAggregator.DoubleAverageAggregator;
import com.sangupta.gather.GatherAggregator.DoubleMaxAggregator;
import com.sangupta.gather.GatherAggregator.DoubleMinAggregator;
import com.sangupta.gather.GatherAggregator.DoubleSumAggregator;
import com.sangupta.gather.GatherAggregator.LongAverageAggregator;
import com.sangupta.gather.GatherAggregator.LongMaxAggregator;
import com.sangupta.gather.GatherAggregator.LongMinAggregator;
import com.sangupta.gather.GatherAggregator.LongSumAggregator;
import com.sangupta.gather.GatherAggregator.UniqueAggregator;

public class TestGatherAggregator {
	
	@Test
	public void testUniqueAggregator() {
		UniqueAggregator aggregator = new UniqueAggregator();
		
		Object instance = new Object();
		Assert.assertEquals(0, aggregator.getResult(0));
		
		aggregator.aggregate(0, instance);
		Assert.assertEquals(1, aggregator.getResult(0));
		
		aggregator.aggregate(0, instance);
		aggregator.aggregate(0, instance);
		aggregator.aggregate(0, instance);
		Assert.assertEquals(1, aggregator.getResult(0));
		
		aggregator.aggregate(0, new Object());
		Assert.assertEquals(2, aggregator.getResult(0));
		
		aggregator.aggregate(0, new Object());
		aggregator.aggregate(0, new Object());
		aggregator.aggregate(0, new Object());
		Assert.assertEquals(5, aggregator.getResult(0));
	}

	@Test
	public void testCountingAggregator() {
		CountingAggregator aggregator = new CountingAggregator();
		
		Assert.assertEquals(0, aggregator.getResult(0));
		
		aggregator.aggregate(0, null);
		Assert.assertEquals(1, aggregator.getResult(0));
		
		aggregator.aggregate(0, null);
		aggregator.aggregate(0, null);
		aggregator.aggregate(0, null);
		Assert.assertEquals(4, aggregator.getResult(0));
	}
	
	@Test
	public void testDoubleMinAggregator() {
		DoubleMinAggregator aggregator = new DoubleMinAggregator();
		
		Random random = new Random();
		double result = Double.MAX_VALUE;
		for(int index = 0; index < 100000; index++) {
			double value = random.nextDouble();
			if(result > value) {
				result = value;
			}
			
			aggregator.aggregate(index, value);
		}
		
		Assert.assertEquals(result, aggregator.getResult(0));
	}
	
	@Test
	public void testDoubleMaxAggregator() {
		DoubleMaxAggregator aggregator = new DoubleMaxAggregator();
		
		Random random = new Random();
		double result = Double.MIN_VALUE;
		for(int index = 0; index < 100000; index++) {
			double value = random.nextDouble();
			if(result < value) {
				result = value;
			}
			
			aggregator.aggregate(index, value);
		}
		
		Assert.assertEquals(result, aggregator.getResult(0));
	}
	
	@Test
	public void testDoubleSumAggregator() {
		DoubleSumAggregator aggregator = new DoubleSumAggregator();
		
		Random random = new Random();
		double result = 0;
		for(int index = 0; index < 100000; index++) {
			double value = random.nextDouble();
			result += value;
			
			aggregator.aggregate(index, value);
		}
		
		Assert.assertEquals(result, aggregator.getResult(0));
	}
	
	@Test
	public void testDoubleAverageAggregator() {
		DoubleAverageAggregator aggregator = new DoubleAverageAggregator();
		
		Random random = new Random();
		double result = 0;
		int counted = 0;
		for(int index = 0; index < 100000; index++) {
			double value = random.nextDouble();
			result += value;
			counted++;
			
			aggregator.aggregate(index, value);
		}
		
		Assert.assertEquals(result / counted, aggregator.getResult(counted));
	}
	
	@Test
	public void testLongMinAggregator() {
		LongMinAggregator aggregator = new LongMinAggregator();
		
		Random random = new Random();
		long result = Long.MAX_VALUE;
		for(int index = 0; index < 100000; index++) {
			long value = random.nextLong();
			if(result > value) {
				result = value;
			}
			
			aggregator.aggregate(index, value);
		}
		
		Assert.assertEquals(result, aggregator.getResult(0));
	}
	
	@Test
	public void testLongMaxAggregator() {
		LongMaxAggregator aggregator = new LongMaxAggregator();
		
		Random random = new Random();
		long result = Long.MIN_VALUE;
		for(int index = 0; index < 100000; index++) {
			long value = random.nextLong();
			if(result < value) {
				result = value;
			}
			
			aggregator.aggregate(index, value);
		}
		
		Assert.assertEquals(result, aggregator.getResult(0));
	}
	
	@Test
	public void testLongSumAggregator() {
		LongSumAggregator aggregator = new LongSumAggregator();
		
		Random random = new Random();
		long result = 0;
		for(int index = 0; index < 100000; index++) {
			long value = random.nextLong();
			result += value;
			
			aggregator.aggregate(index, value);
		}
		
		Assert.assertEquals(result, aggregator.getResult(0));
	}
	
	@Test
	public void testLongAverageAggregator() {
		LongAverageAggregator aggregator = new LongAverageAggregator();
		
		Random random = new Random();
		long result = 0;
		int counted = 0;
		for(int index = 0; index < 100000; index++) {
			long value = random.nextLong();
			result += value;
			counted++;
			
			aggregator.aggregate(index, value);
		}
		
		Assert.assertEquals(result / counted, aggregator.getResult(counted));
	}
}
