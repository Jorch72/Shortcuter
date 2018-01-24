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

package net.malisis.shortcuter.network;

import io.netty.buffer.ByteBuf;
import net.malisis.core.network.IMalisisMessageHandler;
import net.malisis.core.registry.AutoLoad;
import net.malisis.shortcuter.Shortcut;
import net.malisis.shortcuter.Shortcuter;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

/**
 * @author Ordinastie
 *
 */
@AutoLoad(true)
public class SwapMessage implements IMalisisMessageHandler<SwapMessage.Packet, IMessage>
{

	public SwapMessage()
	{
		Shortcuter.network.registerMessage(this, SwapMessage.Packet.class, Side.SERVER);
	}

	@Override
	public void process(Packet message, MessageContext ctx)
	{
		Shortcut.swap(IMalisisMessageHandler.getPlayer(ctx), message.slot1, message.slot2);
	}

	public static void swap(int slot1, int slot2)
	{
		Shortcuter.network.sendToServer(new Packet(slot1, slot2));
	}

	/**
	 * @author Ordinastie
	 *
	 */
	public static class Packet implements IMessage
	{
		private int slot1;
		private int slot2;

		public Packet()
		{}

		public Packet(int slot1, int slot2)
		{
			this.slot1 = slot1;
			this.slot2 = slot2;
		}

		@Override
		public void fromBytes(ByteBuf buf)
		{
			slot1 = buf.readInt();
			slot2 = buf.readInt();
		}

		@Override
		public void toBytes(ByteBuf buf)
		{
			buf.writeInt(slot1);
			buf.writeInt(slot2);
		}
	}
}
