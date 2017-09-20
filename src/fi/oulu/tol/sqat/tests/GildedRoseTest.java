package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

// Example scenarios for testing
//   Item("+5 Dexterity Vest", 10, 20));
//   Item("Aged Brie", 2, 0));
//   Item("Elixir of the Mongoose", 5, 7));
//   Item("Sulfuras, Hand of Ragnaros", 0, 80));
//   Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
//   Item("Conjured Mana Cake", 3, 6));

	  
	
	@Test
	public void testUpdateEndOfDay_RegularItemQualityDropTo10() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("+5 Dexterity Vest", 10, 20) );
		
		// Act
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemVest = items.get(0);
		assertEquals("Regular Item quality should be degraded by the count of days = 10",10, itemVest.getQuality());
		assertEquals("Regular Item selling days should be 0",0, itemVest.getSellIn());
		}
	
	@Test
	public void testUpdateEndOfDay_RegularItemSellingDateShouldNotBeNegative() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("+5 Dexterity Vest", 10, 20) );
		
		// Act
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemVest = items.get(0);
		assertEquals("Regular Item should be 8",8, itemVest.getQuality());	
		assertEquals("Regular Item Selling days should be 0",0, itemVest.getSellIn());
	}
	
	@Test
	public void testUpdateEndOfDay_RegularItemQualityShouldNotBeNegative() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("+5 Dexterity Vest", 10, 0) );
		
		// Act
		store.updateEndOfDay();
		
			
		// Assert
		List<Item> items = store.getItems();
		Item itemVest = items.get(0);
		assertEquals("Regular Item should be 0",0, itemVest.getQuality());	
		assertEquals("Regular Item days should be 8",9, itemVest.getSellIn());
	}
	
	@Test
	public void testUpdateEndOfDay_AgedBrie_Quality_10_11() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 2, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(11, itemBrie.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_AgedBrieSellingDaysNegative_Quality12() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 1, 10) );
		
		// Act
		store.updateEndOfDay();
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals("Brie quality should be 12",12, itemBrie.getQuality());
		assertEquals("Brie Sellin days should be 0",0, itemBrie.getSellIn());
	}
	
	@Test
	public void testUpdateEndOfDay_ItemQualityShouldBe50() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 5, 49) );
		
		// Act
		store.updateEndOfDay();
		
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals("Brie quality should be 50",50, itemBrie.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_ItemQualityShouldNotExceed50() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 5, 49) );
		
		// Act
		store.updateEndOfDay();
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals("Brie quality should be 50",50, itemBrie.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_SulfurasQualityShouldNotChange() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80) );
		
		// Act
		store.updateEndOfDay();
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemSulfuras = items.get(0);
		assertEquals("Sulfuras quality should be 80",80, itemSulfuras.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_SulfurasSellingDaysShouldNotChange() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80) );
		
		// Act
		store.updateEndOfDay();
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemSulfuras = items.get(0);
		assertEquals("Sulfuras Selling Days should be 0",0, itemSulfuras.getSellIn());
	}
	
	@Test
	public void testUpdateEndOfDay_BackstageTicketQualityRisesBy1OnDay11() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 15) );
		
		// Act
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		
		
		// Assert
		List<Item> items = store.getItems();
		Item itemPasses = items.get(0);
		assertEquals("Backstage passes quality should be 19",19, itemPasses.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_BackstageTicketQualityRisesBy2nDay10() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 11, 15) );
		
		// Act
		store.updateEndOfDay();
		
		
		
		// Assert
		List<Item> items = store.getItems();
		Item itemPasses = items.get(0);
		assertEquals("Backstage passes quality should be 17",17, itemPasses.getQuality());
		assertEquals("Backstage passes selling days should be 10",10, itemPasses.getSellIn());
	}
	
	@Test
	public void testUpdateEndOfDay_BackstageTicketQualityRisesBy2nDay6() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 9, 10) );
		
		// Act
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		
		
		// Assert
		List<Item> items = store.getItems();
		Item itemPasses = items.get(0);
		assertEquals("Backstage passes quality should be 16",16, itemPasses.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_BackstageTicketQualityRisesBy3OnDay5() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 6, 15) );
		
		// Act
		store.updateEndOfDay();
		
		
		
		// Assert
		List<Item> items = store.getItems();
		Item itemPasses = items.get(0);
		assertEquals("Backstage passes quality should be 18",18, itemPasses.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_BackstageTicketQualityRisesBy3nDay1() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 4, 10) );
		
		// Act
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		
		
		
		// Assert
		List<Item> items = store.getItems();
		Item itemPasses = items.get(0);
		assertEquals("Backstage passes quality should be 19",19, itemPasses.getQuality());
		assertEquals("Backstage passes selling days should be 1",1, itemPasses.getSellIn());
	}
	
	@Test
	public void testUpdateEndOfDay_BackstageTicketQualityDropsToZeroOnDay0() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 4, 10) );
		
		// Act
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		
			
		// Assert
		List<Item> items = store.getItems();
		Item itemPasses = items.get(0);
		assertEquals("Backstage passes quality should be 0",0, itemPasses.getQuality());
		assertEquals("Backstage passes selling days should be 0",0, itemPasses.getSellIn());
		
	}
	
	@Test
	public void testUpdateEndOfDay_BackstageTicketSellingDaysShouldNotBeNegative() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 2, 10) );
		
		// Act
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		
			
		// Assert
		List<Item> items = store.getItems();
		Item itemPasses = items.get(0);
		assertEquals("Backstage passes selling days should be 0",0, itemPasses.getSellIn());
		
	}
	
}
