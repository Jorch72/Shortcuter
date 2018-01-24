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

import java.util.Collection;

import net.malisis.core.client.gui.ClipArea;
import net.malisis.core.client.gui.GuiRenderer;
import net.malisis.core.client.gui.MalisisGui;
import net.malisis.core.client.gui.component.container.UIListContainer;
import net.malisis.core.client.gui.element.XYResizableGuiShape;
import net.malisis.core.renderer.font.FontOptions;
import net.malisis.core.renderer.font.MalisisFont;
import net.malisis.core.renderer.icon.provider.GuiIconProvider;
import net.minecraft.item.ItemStack;

/**
 * @author Ordinastie
 *
 */
public class SearchResults extends UIListContainer<SearchResults, ItemStack>
{
	private SearchBox searchBox;
	private int elementHeight = 16;
	private int maxDisplayedResults = 10;
	private FontOptions options = FontOptions.builder().color(0xFFFFFF).build();
	private FontOptions noResultOptions = FontOptions.builder().color(0x999999).italic().build();

	//same as UISelect options
	protected GuiIconProvider backgroundIcons;

	public SearchResults(MalisisGui gui, SearchBox searchBox)
	{
		super(gui);
		this.searchBox = searchBox;
		gui.addToScreen(this);
		setZIndex(searchBox.getZIndex() + 1);
		setElementSpacing(1);
		hide();

		options = FontOptions.builder().color(0xFFFFFF).shadow().build();
		//hoverOptions = options.toBuilder().color(0xAAAA33).build();

		shape = new XYResizableGuiShape(1);
		backgroundIcons = new GuiIconProvider(gui.getGuiTexture().getXYResizableIcon(200, 30, 9, 12, 1));
	}

	@Override
	public void setElements(Collection<ItemStack> elements)
	{
		super.setElements(elements);
	}

	@Override
	public int getWidth()
	{
		return searchBox.getWidth();
	}

	@Override
	public int getHeight()
	{
		if (elements.size() == 0)
			return elementHeight;

		return (elementHeight + elementSpacing) * Math.min(elements.size(), maxDisplayedResults);
	}

	@Override
	public int getLeftPadding()
	{
		return 0;
	}

	@Override
	public int getRightPadding()
	{
		return 0;
	}

	@Override
	public int getTopPadding()
	{
		return 0;
	}

	@Override
	public int getBottomPadding()
	{
		return 0;
	}

	@Override
	public ClipArea getClipArea()
	{
		return new ClipArea(this, 1);
	}

	public void display()
	{
		setPosition(searchBox.screenX(), searchBox.screenY() + searchBox.getHeight());
		setVisible(true);
	}

	public void hide()
	{
		setVisible(false);
	}

	@Override
	public int getElementHeight(ItemStack element)
	{
		return 16;
	}

	@Override
	public void drawBackground(GuiRenderer renderer, int mouseX, int mouseY, float partialTick)
	{
		rp.iconProvider.set(backgroundIcons);

		renderer.drawShape(shape, rp);
		renderer.next();
	}

	@Override
	public void drawEmpty(GuiRenderer renderer, int mouseX, int mouseY, float partialTick)
	{
		renderer.drawText(MalisisFont.minecraftFont, "shortcuter.gui.search.no_result", 5, 5, 0, noResultOptions);
	}

	@Override
	public void drawElementBackground(GuiRenderer renderer, int mouseX, int mouseY, float partialTick, ItemStack itemStack, boolean isHovered)
	{

	}

	@Override
	public void drawElementForeground(GuiRenderer renderer, int mouseX, int mouseY, float partialTick, ItemStack itemStack, boolean isHovered)
	{
		if (isHovered)
			renderer.drawRectangle(1, 1, 1, getWidth() - 2, getElementHeight(itemStack) + 1, 0x999999, 255);
		//draw ItemStack
		renderer.drawItemStack(itemStack, 1, 1);
		//draw display name
		FontOptions opt = options;
		if (isHovered)
			opt = options.toBuilder().color(0xFFFF00).build();
		renderer.drawText(MalisisFont.minecraftFont, itemStack.getDisplayName(), 18, 5, 1, opt);
	}

}
