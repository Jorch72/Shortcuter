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

import net.malisis.core.client.gui.MalisisGui;
import net.malisis.core.client.gui.component.container.UIContainer;
import net.malisis.core.client.gui.component.decoration.UILabel;
import net.malisis.core.client.gui.render.BackgroundTexture.WindowBackground;

/**
 * @author Ordinastie
 *
 */
public class ConfigGui extends MalisisGui
{
	@Override
	public void construct()
	{
		UIContainer<?> window = new UIContainer<>(this, 400, 300);
		window.setBackground(new WindowBackground(this));

		addSearch(window);

		addToScreen(window);
	}

	private void addSearch(UIContainer<?> window)
	{
		UIContainer<?> cont = new UIContainer<>(this);

		//label
		UILabel label = new UILabel(this, "shortcuter.gui.search.label");
		//seachbox
		SearchBox searchBox = new SearchBox(this);
		searchBox.setPosition(80, 0);
		searchBox.setSize(150, 12);

		cont.add(label);
		cont.add(searchBox);

		window.add(cont);

	}
}
