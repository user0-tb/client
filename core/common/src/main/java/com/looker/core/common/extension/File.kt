package com.looker.core.common.extension

import kotlinx.coroutines.*
import java.io.File
import java.io.InputStream

val File.size: Long?
	get() = if (exists()) length().takeIf { it > 0L } else null

suspend infix fun InputStream.writeTo(file: File) = withContext(Dispatchers.IO) {
	val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
	var bytesRead = read(buffer)
	file.outputStream().use { output ->
		while (bytesRead != -1) {
			ensureActive()
			output.write(buffer, 0, bytesRead)
			bytesRead = read(buffer)
		}
		output.flush()
	}
}