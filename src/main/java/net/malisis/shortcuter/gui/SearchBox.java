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

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import com.google.common.collect.Lists;

import net.malisis.core.client.gui.MalisisGui;
import net.malisis.core.client.gui.component.container.UIContainer;
import net.malisis.core.client.gui.component.container.UIListContainer;
import net.malisis.core.client.gui.component.decoration.UILabel;
import net.malisis.core.client.gui.component.interaction.UITextField;
import net.malisis.core.client.gui.render.ColoredBackground;
import net.malisis.core.renderer.font.FontOptions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ISearchTree;
import net.minecraft.client.util.SearchTreeManager;
import net.minecraft.item.ItemStack;

/**
 * @author Ordinastie
 *
 */
public class SearchBox extends UITextField
{
	protected UIContainer<?> resultContainer;
	protected UIListContainer<ItemStack> listContainer;
	protected UILabel noresult;

	public SearchBox(MalisisGui gui)
	{
		super(gui, false);

		resultContainer = new UIContainer<>(gui);
		resultContainer.setBackground(new ColoredBackground(0xAAAAAA, 2, 0xFFFFFF));
		resultContainer.setVisible(false);

		noresult = new UILabel(gui, "shortcuter.gui.search.no_result");
		noresult.setFontOptions(FontOptions.builder().color(0x999999).italic().build());

		UIListContainer<ItemStack> listContainer;
		listContainer = new UIListContainer<>(gui);
		listContainer.setElementSpacing(1);
		listContainer.setComponentFactory(SearchResult::new);

		listContainer.add(listContainer);

	}

	@Override
	public void onAddedToScreen()
	{
		super.onAddedToScreen();
		getGui().addToScreen(resultContainer);
	}

	public void updateResults()
	{
		String input = getText();

		if (input.length() <= 0)
		{
			listContainer.setElements(Collections.emptyList());
			hideResults();
			return;
		}

		List<ItemStack> itemStacks = Lists.newArrayList();
		ISearchTree<ItemStack> st = Minecraft.getMinecraft().getSearchTree(SearchTreeManager.ITEMS);
		itemStacks.addAll(st.search(input.toLowerCase(Locale.ROOT)));
		listContainer.setElements(itemStacks);

		noresult.setVisible(itemStacks.size() == 0);

		showResults();
	}

	public void showResults()
	{
		resultContainer.setPosition(screenX(), screenY() + getHeight());
		resultContainer.setVisible(true);
	}

	public void hideResults()
	{
		resultContainer.setVisible(false);
	}

	@Override
	public void setFocused(boolean focused)
	{
		if (focused)
			updateResults();
		else
			hideResults();
		super.setFocused(focused);
	}

	@Override
	public boolean onKeyTyped(char keyChar, int keyCode)
	{
		boolean b = super.onKeyTyped(keyChar, keyCode);

		updateResults();

		return b;
	}
}
