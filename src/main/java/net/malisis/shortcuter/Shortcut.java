/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Ordinastie
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.malisis.shortcuter;

import org.lwjgl.input.Keyboard;

import net.malisis.core.inventory.MalisisSlot;
import net.malisis.core.inventory.player.PlayerInventory;
import net.malisis.core.util.ItemUtils;
import net.malisis.core.util.Utils;
import net.malisis.shortcuter.network.SwapMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

/**
 * @author Ordinastie
 *
 */
public class Shortcut
{

	/** Keyboard key to press. */
	protected int key;
	/** ItemStack to place with the key. */
	protected ItemStack itemStack;
	/** Last slot number the itemStack was found. */
	protected int lastSlot = -1;

	protected Shortcut(int key, ItemStack itemStack)
	{
		this.key = key;
		this.itemStack = itemStack;
	}

	public int getKey()
	{
		return key;
	}

	public ItemStack getItemStack()
	{
		return itemStack;
	}

	public int lastSlot()
	{
		return lastSlot;
	}

	protected int findSlot(InventoryPlayer inventory)
	{
		//check if itemStack still in last detected slot
		if (lastSlot != -1 && ItemUtils.areItemStacksStackable(itemStack, inventory.getStackInSlot(lastSlot)))
			return lastSlot;

		lastSlot = inventory.getSlotFor(itemStack);
		return lastSlot;
	}

	public boolean execute()
	{
		//check if pressed
		if (!Keyboard.isKeyDown(key))
			return false;
		//Keyboard.isKeyDown(key)

		EntityPlayer player = Utils.getClientPlayer();
		int shortcutSlot = findSlot(player.inventory);
		if (shortcutSlot == -1)
			return false;

		SwapMessage.swap(shortcutSlot, player.inventory.currentItem);
		swap(player, shortcutSlot, player.inventory.currentItem);
		Shortcuter.rightClickMouse();
		SwapMessage.swap(shortcutSlot, player.inventory.currentItem);
		swap(player, shortcutSlot, player.inventory.currentItem);

		return true;
	}

	public static void swap(EntityPlayer player, int s1, int s2)
	{
		PlayerInventory inventory = new PlayerInventory(player);
		MalisisSlot slot1 = inventory.getSlot(s1);
		MalisisSlot slot2 = inventory.getSlot(s2);
		ItemStack sis = slot1.extract();
		ItemStack ais = slot2.extract();
		slot1.insert(ais);
		slot2.insert(sis);
	}

	public static Shortcut create(int key, ItemStack itemStack)
	{
		return new Shortcut(key, itemStack);
	}

}
