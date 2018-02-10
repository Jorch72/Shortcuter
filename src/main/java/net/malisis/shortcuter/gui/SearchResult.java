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

package net.malisis.shortcuter.gui;

import net.malisis.core.client.gui.GuiRenderer;
import net.malisis.core.client.gui.MalisisGui;
import net.malisis.core.client.gui.component.UIComponent;
import net.malisis.core.client.gui.component.container.UIContainer;
import net.malisis.core.renderer.font.FontOptions;
import net.malisis.core.renderer.font.MalisisFont;
import net.malisis.core.renderer.icon.provider.GuiIconProvider;
import net.minecraft.item.ItemStack;

/**
 * @author Ordinastie
 *
 */
public class SearchResult extends UIContainer<SearchResult>
{
	private ItemStack itemStack;
	private FontOptions options = FontOptions.builder().color(0xFFFFFF).build();
	private FontOptions hoverOptions = FontOptions.builder().color(0xFFFF00).build();

	//same as UISelect options
	protected GuiIconProvider backgroundIcons;

	public SearchResult(MalisisGui gui, ItemStack itemStack)
	{
		super(gui);
		this.itemStack = itemStack;

		options = FontOptions.builder().color(0xFFFFFF).shadow().build();
		hoverOptions = FontOptions.builder().color(0xAAAA33).build();

		setSize(UIComponent.INHERITED, 16);
	}

	@Override
	public void drawBackground(GuiRenderer renderer, int mouseX, int mouseY, float partialTick)
	{
		if (!isHovered())
			return;

		renderer.drawRectangle(0, 0, 0, getWidth(), getHeight(), 0x999999, 255);
	}

	@Override
	public void drawForeground(GuiRenderer renderer, int mouseX, int mouseY, float partialTick)
	{
		//draw ItemStack
		renderer.drawItemStack(itemStack, 1, 1);
		//draw display name
		renderer.drawText(MalisisFont.minecraftFont, itemStack.getDisplayName(), 18, 5, 1, isHovered() ? hoverOptions : options);
	}

}
