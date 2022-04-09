// To Refactor this code, we need to extract the code in new methods
// 1.first of all we will extraxt a method called DecrementQualityForNormalItems(int i).
// In this method we will extract the line of code that is  causing duplication into a single method.

private void DecrementQualityForNormalItems(int i){
    if (Items[i].Quality > 0)
			{
				if (Items[i].Name != "Sulfuras, Hand of Ragnaros")
				{
					Items[i].Quality = Items[i].Quality - 1;
				}
			}
}

//2. we will now create a method IncrementQuality(int i) 
// and extract the piece of code that is incrementing the quality of 
// various items at several places to reduce the bad smells in our code. 
// there is a complication in it, this piece of code contains a condition which is hard to modify that is
// Quality < 50 on the outrr side to solve this we are creating this method and writing this condition inside the method
// so by doing this technique there will be no effect on the code

private void IncrementQuality(int i){
    if (Items[i].Quality < 50)
    {
        Items[i].Quality = Items[i].Quality + 1;
    }
}

//3.Now we are creating a method for items that age well and for this we are extracting two different 
// piece of code and merging into one also we have given it a descriptive name so that it is more understandable 

private void  UpdateQualityForItemsThatAgeWell(int i){
 IncrementQuality(i);
    if (Items[i].Name == "Backstage passes to a TAFKAL80ETC concert")
    {
        if (Items[i].SellIn < 11)
        {
            IncrementQuality(i);
        }
        if (Items[i].SellIn < 6)
        {
            IncrementQuality(i);
        }
    }
}

// 4.At the end i’ll make a method call UpdateQualityForExpiredItems this method will
// contain all the loops and ifs used at multiple places at several times making the code more complex and 
// hard to understand into a single method UpdateQualityForExpiredItems

private void UpdateQualityForExpiredItems(int i)
{
    if (Items[i].Name != "Aged Brie")
			{
				if (Items[i].Name != "Backstage passes to a TAFKAL80ETC concert")
				{
					DecrementQualityForNormalItems(i);
				}
				else
				{
					Items[i].Quality = Items[i].Quality - Items[i].Quality;
				}
			}
			else
			{
				IncrementQuality(i);
			}
}
// 5. At the end the code will look like this
public void UpdateQuality()
{
	for (var i = 0; i < Items.Count; i++)
	{
		if (Items[i].Name != "Aged Brie" && Items[i].Name != "Backstage passes to a TAFKAL80ETC concert")
		{
			DecrementQualityForNormalItems(i);
		}
		else
		{
			UpdateQualityForItemsThatAgeWell(i);
		}

		if (Items[i].Name != "Sulfuras, Hand of Ragnaros")
		{
			Items[i].SellIn = Items[i].SellIn - 1;
		}

		if (Items[i].SellIn < 0)
		{
			UpdateQualityForExpiredItems(i);
		}
	}
}
//this is now much shorter and easier to understand furthermore we have removed all the bad smells and duplications that makes it longer and complex.