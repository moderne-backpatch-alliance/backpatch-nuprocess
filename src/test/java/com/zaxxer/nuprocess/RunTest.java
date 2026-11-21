/*
 * Copyright (C) 2013 Brett Wooldridge
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zaxxer.nuprocess;

import org.junit.Before;
import org.junit.Test;

public class RunTest
{
   private String command;

   @Before
   public void setup()
   {
      command = "cat";
      if (System.getProperty("os.name").toLowerCase().contains("win")) {
         command = "src\\test\\java\\com\\zaxxer\\nuprocess\\cat.exe";
      }
   }

   @Test(expected = IllegalArgumentException.class)
   public void nullCommandViaCommandMutationWithStart() {
      NuProcessBuilder pb = new NuProcessBuilder(new NullProcessHandler(), command);
      pb.command().add("--foo\0--bar");
      pb.start();
   }

   @Test(expected = IllegalArgumentException.class)
   public void nullCommandViaConstructorWithStart() {
      NuProcessBuilder pb = new NuProcessBuilder(new NullProcessHandler(), command, "--foo\0--bar");
      pb.start();
   }

   private static class NullProcessHandler extends NuAbstractProcessHandler {
   }
}
