/*
 * Copyright (c) 2019, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package jdk.incubator.jpackage.internal;

import java.io.File;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import static jdk.incubator.jpackage.internal.StandardBundlerParam.*;

final class FileAssociation {
    void verify() {
        if (extensions.isEmpty()) {
            Log.error(I18N.getString(
                    "message.creating-association-with-null-extension"));
        }
    }

    static List<FileAssociation> fetchFrom(Map<String, ? super Object> params) {
        String launcherName = APP_NAME.fetchFrom(params);

        return FILE_ASSOCIATIONS.fetchFrom(params).stream().filter(
                Objects::nonNull).map(fa -> {
                    FileAssociation assoc = new FileAssociation();

                    assoc.launcherPath = Path.of(launcherName);
                    assoc.description = FA_DESCRIPTION.fetchFrom(fa);
                    assoc.extensions = Optional.ofNullable(
                            FA_EXTENSIONS.fetchFrom(fa)).orElse(Collections.emptyList());
                    assoc.mimeTypes = Optional.ofNullable(
                            FA_CONTENT_TYPE.fetchFrom(fa)).orElse(Collections.emptyList());

                    File icon = FA_ICON.fetchFrom(fa);
                    if (icon != null) {
                        assoc.iconPath = icon.toPath();
                    }

                    return assoc;
                }).collect(Collectors.toList());
    }

    Path launcherPath;
    Path iconPath;
    List<String> mimeTypes;
    List<String> extensions;
    String description;
}
