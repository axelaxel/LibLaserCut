/**
 * This file is part of VisiCut.
 * Copyright (C) 2012 Thomas Oster <thomas.oster@rwth-aachen.de>
 * RWTH Aachen University - 52062 Aachen, Germany
 * 
 *     VisiCut is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *    VisiCut is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 * 
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with VisiCut.  If not, see <http://www.gnu.org/licenses/>.
 **/
package com.t_oster.liblasercut.dithering;

import com.t_oster.liblasercut.BlackWhiteRaster;
import com.t_oster.liblasercut.Customizable;
import com.t_oster.liblasercut.GreyscaleRaster;
import com.t_oster.liblasercut.TimeIntensiveOperation;

/**
 *
* @author Thomas Oster <thomas.oster@rwth-aachen.de>
 */
public abstract class DitheringAlgorithm extends TimeIntensiveOperation implements Customizable, Cloneable
{
  
  protected GreyscaleRaster src;
  protected BlackWhiteRaster target;

  protected void setBlack(int x, int y, boolean black)
  {
    if (target != null)
    {
      target.setBlack(x, y, black);
    }
    else
    {
      src.setGreyScale(x, y, black ? 0 : 255);
    }
  }

  public BlackWhiteRaster dither(GreyscaleRaster input)
  {
    src = input;
    target = new BlackWhiteRaster(input.getWidth(), input.getHeight());
    doDithering();
    return target;
  }

  public void ditherDirect(GreyscaleRaster input)
  {
    src = input;
    target = null;
    doDithering();
  }
  
  public void ditherDirect(GreyscaleRaster input, BlackWhiteRaster output)
  {
    src = input;
    target = output;
    doDithering();
  }
  
  protected abstract void doDithering();
  
  @Override
  public String[] getPropertyKeys() {
    return new String[0];
  }

  @Override
  public void setProperty(String key, Object value) {
  }

  @Override
  public Object getProperty(String key) {
    return null;
  }
  
  @Override
  public abstract DitheringAlgorithm clone();
  
  @Override
  public abstract String toString();
}
